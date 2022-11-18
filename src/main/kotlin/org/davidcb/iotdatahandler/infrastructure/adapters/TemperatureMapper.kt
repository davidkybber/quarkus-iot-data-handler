package org.davidcb.iotdatahandler.infrastructure.adapters

import org.davidcb.iotdatahandler.core.domain.models.Temperature
import org.davidcb.iotdatahandler.infrastructure.adapters.entities.TemperatureEntity

object TemperatureMapper {
    fun toEntity(temperature: Temperature): TemperatureEntity =
        TemperatureEntity(degrees = temperature.degrees, humidity = temperature.humidity)

    fun toDomain(temperatureEntity: TemperatureEntity) =
        Temperature(degrees = temperatureEntity.degrees!!, humidity = temperatureEntity.humidity)
}
