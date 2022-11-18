package org.davidcb.iotdatahandler.infrastructure.adapters

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.models.Temperature
import org.davidcb.iotdatahandler.core.ports.TemperaturePersistencePort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TemperaturePersistenceAdapter(
    val temperatureRepository: TemperatureRepository,
) : TemperaturePersistencePort {
    override fun persist(temperature: Temperature): Uni<Temperature> {
        val temperatureEntity = temperatureRepository.persist(TemperatureMapper.toEntity(temperature))
        return temperatureEntity.onItem().transform(TemperatureMapper::toDomain)
    }
}
