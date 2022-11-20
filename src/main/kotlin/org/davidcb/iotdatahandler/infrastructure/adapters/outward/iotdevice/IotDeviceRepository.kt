package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice

import io.smallrye.mutiny.Uni
import org.bson.Document
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.ReactiveRepository
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.entities.IotDeviceEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class IotDeviceRepository : ReactiveRepository<IotDeviceEntity>() {
    override fun IotDeviceEntity.idFilter(): Document =
        Document.parse("""{ "iotDeviceId": "$iotDeviceId" }""")

    fun upsert(iotDeviceEntity: IotDeviceEntity): Uni<IotDeviceEntity> = iotDeviceEntity.upsert()
}
