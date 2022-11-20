package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.domain.model.IotDevice
import org.davidcb.iotdatahandler.core.ports.IotDevicePersistencePort
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.entities.IotDeviceEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class IotDevicePersistenceAdapter(
    val iotDeviceRepository: IotDeviceRepository,
) : IotDevicePersistencePort {
    override fun persist(iotDevice: IotDevice): Uni<IotDevice> {
        val iotDeviceEntity = iotDeviceRepository.upsert(IotDeviceMapper.toEntity(iotDevice))
        return iotDeviceEntity.onItem().transform(IotDeviceMapper::toDomain)
    }

    override fun getAll(): Uni<List<IotDevice>> {
        return iotDeviceRepository.findAll().list<IotDeviceEntity>().onItem().transform { it.map(IotDeviceMapper::toDomain) }
    }
}
