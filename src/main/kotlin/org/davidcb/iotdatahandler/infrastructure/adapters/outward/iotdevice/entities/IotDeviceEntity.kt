package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.entities

import io.quarkus.mongodb.panache.common.MongoEntity

@MongoEntity(collection = "iot-device")
data class IotDeviceEntity(
    var iotDeviceId: String,
    var deviceBatteryStatus: String? = null,
)
