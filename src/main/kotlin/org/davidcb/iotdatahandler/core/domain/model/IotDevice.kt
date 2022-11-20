package org.davidcb.iotdatahandler.core.domain.model

data class IotDevice(
    val iotDeviceId: IotDeviceId,
    val deviceBatteryStatus: String?,
)
