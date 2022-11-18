package org.davidcb.iotdatahandler.core.ports

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.models.Temperature

interface TemperaturePersistencePort {
    fun persist(temperature: Temperature): Uni<Temperature>
}
