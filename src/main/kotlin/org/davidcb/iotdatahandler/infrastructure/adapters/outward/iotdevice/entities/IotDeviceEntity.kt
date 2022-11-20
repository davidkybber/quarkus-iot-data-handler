package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.entities

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.codecs.pojo.annotations.BsonId

@MongoEntity(collection = "iot-device")
data class IotDeviceEntity(
    @BsonId
    var iotDeviceId: String? = null,
    var deviceBatteryStatus: String? = null,
)
