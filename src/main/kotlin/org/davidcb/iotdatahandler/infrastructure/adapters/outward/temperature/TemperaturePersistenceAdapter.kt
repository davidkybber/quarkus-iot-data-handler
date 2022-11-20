package org.davidcb.iotdatahandler.infrastructure.adapters.outward.temperature

import io.quarkus.panache.common.Sort
import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.model.Temperature
import org.davidcb.iotdatahandler.core.ports.TemperaturePersistencePort
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.temperature.entities.TemperatureEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TemperaturePersistenceAdapter(
    val temperatureRepository: TemperatureRepository,
) : TemperaturePersistencePort {
    override fun persist(temperature: Temperature): Uni<Temperature> {
        val temperatureEntity = temperatureRepository.persist(TemperatureMapper.toEntity(temperature))
        return temperatureEntity.onItem().transform(TemperatureMapper::toDomain)
    }

    override fun getLatest(): Uni<Temperature> {
        return temperatureRepository.findAll(Sort.descending("timeStamp")).firstResult<TemperatureEntity>().onItem().transform(TemperatureMapper::toDomain)
    }
}
