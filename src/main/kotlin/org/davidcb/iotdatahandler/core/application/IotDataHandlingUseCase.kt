package org.davidcb.iotdatahandler.core.application

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.models.Temperature
import org.davidcb.iotdatahandler.core.ports.out.TemperaturePersistencePort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class IotDataHandlingUseCase(
    private val temperaturePersistencePort: TemperaturePersistencePort,
) {
    fun persistIotTemperatureData(temperature: Temperature): Uni<Unit> =
        temperaturePersistencePort.persist(temperature).onItem().transform {  }
}
