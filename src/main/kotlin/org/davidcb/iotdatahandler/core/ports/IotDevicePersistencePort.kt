package org.davidcb.iotdatahandler.core.ports

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.model.IotDevice

interface IotDevicePersistencePort {
    fun persist(iotDevice: IotDevice): Uni<IotDevice>
}
