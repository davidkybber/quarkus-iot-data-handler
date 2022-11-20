package org.davidcb.iotdatahandler.infrastructure.adapters.outward

import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.ReturnDocument
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository
import io.smallrye.mutiny.Uni
import org.bson.BsonDateTime
import org.bson.BsonDocument
import org.bson.BsonDocumentWriter
import org.bson.Document
import org.bson.codecs.EncoderContext
import java.time.Instant
import java.time.OffsetDateTime

abstract class ReactiveRepository<T : Any> : ReactivePanacheMongoRepository<T> {
    protected fun T.upsert(): Uni<T> = mongoCollection().findOneAndUpdate(
        idFilter(),
        BsonDocument(set, toBsonDocument()),
        FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER)
    )

    @Suppress("UNCHECKED_CAST")
    protected fun T.`$set`(vararg values: Pair<String, Any?>): Uni<T?> = execute(
        setFieldsQuery(values.filter { it.second != null }.toList() as List<Pair<String, Any>>),
        unsetFieldsQuery(values.filter { it.second == null }.map { it.first })
    )

    private fun unsetFieldsQuery(valueKeys: List<String>): String =
        if (valueKeys.isEmpty()) """ "$unset": {} """
        else valueKeys.joinToString(", ") { """"$it": """"" }
            .let { """ "$unset": { $it } """ }

    private fun setFieldsQuery(valuePairs: List<Pair<String, Any>>): String = valuePairs
        .map {
            when (it.second) {
                is String -> it.first to "\"${it.second}\""
                is Int -> it.first to it.second
                is Boolean -> it.first to it.second
                is OffsetDateTime -> it.first to "new Date(${(it.second as OffsetDateTime?)?.toInstant()?.toEpochMilli()})"
                is Instant -> it.first to "new Date(${(it.second as Instant?)?.toEpochMilli()})"
                else -> throw IllegalArgumentException("Unsupported data type for dynamic MongoDB setter: ${it.second?.javaClass}")
            }
        }
        .joinToString(", ") { "\"${it.first}\": ${it.second}" }
        .let { """ "$set": { $it, "$UPDATE_TIMESTAMP_FIELD": new Date() } """ }

    private fun T.execute(setFieldsQuery: String, unsetFieldsQuery: String): Uni<T?> {
        return mongoCollection().findOneAndUpdate(
            idFilter(),
            // { $set: { key: value, key2: value2, updatedAt: Date.now() }, $unset: { ... } }
            Document.parse("""{$setFieldsQuery,$unsetFieldsQuery}"""),
            FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
        )
    }

    private fun T.toBsonDocument(): BsonDocument {
        val document = BsonDocument()
        val codec = mongoCollection().codecRegistry[this.javaClass]
        codec.encode(BsonDocumentWriter(document), this, EncoderContext.builder().build())
        document[UPDATE_TIMESTAMP_FIELD] = BsonDateTime(Instant.now().toEpochMilli())
        return document
    }

    protected abstract fun T.idFilter(): Document

    protected companion object {
        const val set = "\$set"
        const val unset = "\$unset"
        const val UPDATE_TIMESTAMP_FIELD = "updatedAt"
    }
}

