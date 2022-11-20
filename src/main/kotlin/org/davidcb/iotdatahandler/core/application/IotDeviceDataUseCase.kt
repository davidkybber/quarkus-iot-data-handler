package org.davidcb.iotdatahandler.core.application

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.model.IotDevice
import org.davidcb.iotdatahandler.core.ports.IotDevicePersistencePort
import java.time.Duration
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class IotDeviceDataUseCase(
    private val iotDevicePersistencePort: IotDevicePersistencePort,
) {
    fun persistIotDeviceData(iotDevice: IotDevice): Uni<Unit> =
        iotDevicePersistencePort.persist(iotDevice).onItem().transform { }
            .onFailure().retry().withBackOff(Duration.ofSeconds(10)).atMost(2)

    fun getAllIotDevices(): Uni<List<IotDevice>> =
        iotDevicePersistencePort.getAll()
            .onFailure().retry().withBackOff(Duration.ofSeconds(10)).atMost(2)
}
