package org.davidcb.iotdatahandler.core.application

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.model.Temperature
import org.davidcb.iotdatahandler.core.ports.TemperaturePersistencePort
import java.time.Duration
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class IotDataHandlingUseCase(
    private val temperaturePersistencePort: TemperaturePersistencePort,
) {
    fun persistIotTemperatureData(temperature: Temperature): Uni<Unit> =
        temperaturePersistencePort.persist(temperature).onItem().transform { }
            .onFailure().retry().withBackOff(Duration.ofSeconds(20)).atMost(2)
}
