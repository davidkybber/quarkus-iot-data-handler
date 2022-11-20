package org.davidcb.iotdatahandler.infrastructure.adapters.outward

import org.davidcb.iotdatahandler.core.domain.model.Temperature
import org.davidcb.iotdatahandler.infrastructure.adapters.entities.TemperatureEntity
import org.davidcb.iotdatahandler.infrastructure.adapters.`in`.TemperatureDto
import java.time.Instant

object TemperatureMapper {
    fun toEntity(temperature: Temperature): TemperatureEntity =
        TemperatureEntity(degrees = temperature.degrees, humidity = temperature.humidity, timeStamp = temperature.timeStamp)

    fun toDomain(temperatureEntity: TemperatureEntity) =
        Temperature(degrees = temperatureEntity.degrees!!, humidity = temperatureEntity.humidity!!, temperatureEntity.timeStamp!!)

    fun toDomain(temperatureDto: TemperatureDto) =
        Temperature(degrees = temperatureDto.degrees, humidity = temperatureDto.humidity, timeStamp = Instant.now())
}
