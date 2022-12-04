package org.davidcb.iotdatahandler.infrastructure.adapters.outward.temperature

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Tag
import io.quarkus.panache.common.Sort
import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.model.Temperature
import org.davidcb.iotdatahandler.core.ports.TemperaturePersistencePort
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.temperature.entities.TemperatureEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TemperaturePersistenceAdapter(
    val temperatureRepository: TemperatureRepository,
    val meterRegistry: MeterRegistry,
) : TemperaturePersistencePort {
    override fun persist(temperature: Temperature): Uni<Temperature> {
        meterRegistry.gauge(/* name = */ "temperature", /* tags = */ listOf(Tag.of("device", temperature.iotDeviceId)), /* number = */ temperature.degrees.toBigDecimal())
        meterRegistry.gauge(/* name = */ "humidity", /* tags = */ listOf(Tag.of("device", temperature.iotDeviceId)), /* number = */ temperature.humidity.toBigDecimal())
        val temperatureEntity = temperatureRepository.persist(TemperatureMapper.toEntity(temperature))
        return temperatureEntity.onItem().transform(TemperatureMapper::toDomain)
    }

    override fun getLatest(): Uni<Temperature> {
        return temperatureRepository.findAll(Sort.descending("timeStamp")).firstResult<TemperatureEntity>().onItem().transform(TemperatureMapper::toDomain)
    }
}
