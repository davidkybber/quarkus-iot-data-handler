package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase
import org.davidcb.iotdatahandler.core.domain.model.IotDeviceId
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.entities.IotDeviceEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class IotDeviceRepository : ReactivePanacheMongoRepositoryBase<IotDeviceEntity, IotDeviceId>
