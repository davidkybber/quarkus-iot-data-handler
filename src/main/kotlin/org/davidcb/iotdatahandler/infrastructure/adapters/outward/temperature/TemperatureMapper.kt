package org.davidcb.iotdatahandler.infrastructure.adapters.outward.temperature

import org.davidcb.iotdatahandler.core.domain.model.Temperature
import org.davidcb.iotdatahandler.infrastructure.adapters.`in`.temperature.TemperatureDto
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.temperature.entities.TemperatureEntity
import java.time.Instant

object TemperatureMapper {
    fun toEntity(temperature: Temperature): TemperatureEntity =
        TemperatureEntity(iotDeviceId = temperature.iotDeviceId, degrees = temperature.degrees, humidity = temperature.humidity, timeStamp = temperature.timeStamp)

    fun toDomain(temperatureEntity: TemperatureEntity) =
        Temperature(iotDeviceId = temperatureEntity.iotDeviceId!!, degrees = temperatureEntity.degrees!!, humidity = temperatureEntity.humidity!!, temperatureEntity.timeStamp!!)

    fun toDomain(temperatureDto: TemperatureDto) =
        Temperature(iotDeviceId = temperatureDto.iotDeviceId, degrees = temperatureDto.degrees, humidity = temperatureDto.humidity, timeStamp = Instant.now())

    fun toDto(temperature: Temperature) =
        TemperatureDto(iotDeviceId = temperature.iotDeviceId, degrees = temperature.degrees, humidity = temperature.humidity)
}
