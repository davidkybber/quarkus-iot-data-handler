package org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.entities.IotDeviceEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class IotDeviceRepository : ReactivePanacheMongoRepository<IotDeviceEntity>
