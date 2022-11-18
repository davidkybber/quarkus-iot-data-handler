package org.davidcb.iotdatahandler.infrastructure.adapters.entities

import io.quarkus.mongodb.panache.common.MongoEntity

@MongoEntity(collection = "temperature")
data class TemperatureEntity(
    var degrees: String? = null,
    var humidity: String? = null,
)
