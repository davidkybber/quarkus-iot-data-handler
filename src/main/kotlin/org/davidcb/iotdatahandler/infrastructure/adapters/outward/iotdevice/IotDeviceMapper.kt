package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice

import org.davidcb.iotdatahandler.core.domain.model.IotDevice
import org.davidcb.iotdatahandler.infrastructure.adapters.`in`.iotdevice.IotDeviceDto
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.entities.IotDeviceEntity
import java.time.Instant

object IotDeviceMapper {
    fun toEntity(iotDevice: IotDevice): IotDeviceEntity =
        IotDeviceEntity(iotDeviceId = iotDevice.iotDeviceId, deviceBatteryStatus = iotDevice.deviceBatteryStatus, lastStatusUpdate = iotDevice.lastStatusUpdate)

    fun toDomain(iotDeviceEntity: IotDeviceEntity) =
        IotDevice(iotDeviceId = iotDeviceEntity.iotDeviceId!!, deviceBatteryStatus = iotDeviceEntity.deviceBatteryStatus, lastStatusUpdate = iotDeviceEntity.lastStatusUpdate)

    fun toDomain(iotDeviceDto: IotDeviceDto) =
        IotDevice(iotDeviceId = iotDeviceDto.iotDeviceId, deviceBatteryStatus = iotDeviceDto.deviceBatteryStatus, lastStatusUpdate = Instant.now())
}
