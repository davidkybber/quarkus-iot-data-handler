package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.entities

import io.quarkus.mongodb.panache.common.MongoEntity
import java.time.Instant

@MongoEntity(collection = "iot-device")
data class IotDeviceEntity(
    var iotDeviceId: String? = null,
    var deviceBatteryStatus: String? = null,
    var lastStatusUpdate: Instant? = null,
)
