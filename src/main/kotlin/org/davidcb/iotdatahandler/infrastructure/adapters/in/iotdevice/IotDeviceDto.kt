package org.davidcb.iotdatahandler.infrastructure.adapters.`in`.iotdevice

import org.davidcb.iotdatahandler.core.domain.model.IotDeviceId

data class IotDeviceDto(
    val iotDeviceId: IotDeviceId,
    val deviceBatteryStatus: String?,
)
