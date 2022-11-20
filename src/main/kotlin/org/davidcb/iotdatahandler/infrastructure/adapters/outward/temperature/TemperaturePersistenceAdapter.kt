package org.davidcb.iotdatahandler.infrastructure.adapters.outward.temperature

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.model.Temperature
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
