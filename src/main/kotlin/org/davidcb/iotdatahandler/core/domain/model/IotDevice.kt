package org.davidcb.iotdatahandler.core.domain.model

import java.time.Instant

data class IotDevice(
    val iotDeviceId: IotDeviceId,
    val deviceBatteryStatus: String?,
    val lastStatusUpdate: Instant?,
)
