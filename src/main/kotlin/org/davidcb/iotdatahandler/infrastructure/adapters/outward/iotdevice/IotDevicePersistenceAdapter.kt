package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.model.IotDevice
import org.davidcb.iotdatahandler.core.ports.IotDevicePersistencePort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class IotDevicePersistenceAdapter(
    val iotDeviceRepository: IotDeviceRepository,
) : IotDevicePersistencePort {
    override fun persist(iotDevice: IotDevice): Uni<IotDevice> {
        val iotDeviceEntity = iotDeviceRepository.persist(IotDeviceMapper.toEntity(iotDevice))
        return iotDeviceEntity.onItem().transform(IotDeviceMapper::toDomain)
    }
}
