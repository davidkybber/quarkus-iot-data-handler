package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice

import org.davidcb.iotdatahandler.core.domain.model.IotDevice
import org.davidcb.iotdatahandler.infrastructure.adapters.`in`.iotdevice.IotDeviceDto
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.entities.IotDeviceEntity

object IotDeviceMapper {
    fun toEntity(iotDevice: IotDevice): IotDeviceEntity =
        IotDeviceEntity(iotDeviceId = iotDevice.iotDeviceId, deviceBatteryStatus = iotDevice.deviceBatteryStatus)

    fun toDomain(iotDeviceEntity: IotDeviceEntity) =
        IotDevice(iotDeviceId = iotDeviceEntity.iotDeviceId!!, deviceBatteryStatus = iotDeviceEntity.deviceBatteryStatus)

    fun toDomain(iotDeviceDto: IotDeviceDto) =
        IotDevice(iotDeviceId = iotDeviceDto.iotDeviceId, deviceBatteryStatus = iotDeviceDto.deviceBatteryStatus)
}
