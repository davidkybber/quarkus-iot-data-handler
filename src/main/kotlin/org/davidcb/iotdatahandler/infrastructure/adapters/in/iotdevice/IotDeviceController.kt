package org.davidcb.iotdatahandler.infrastructure.adapters.`in`.iotdevice

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.application.IotDeviceDataUseCase
import org.davidcb.iotdatahandler.core.domain.model.IotDevice
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.iotdevice.IotDeviceMapper.toDomain
import java.lang.Exception
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class IotDeviceController(
    private val iotDeviceDataUseCase: IotDeviceDataUseCase
) {
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/iotdevice")
    @POST
    fun iotDeviceData(iotDeviceDto: IotDeviceDto): String {
        iotDeviceDataUseCase.persistIotDeviceData(toDomain(iotDeviceDto)).subscribe().with(
            {
                println("Stored the value of $iotDeviceDto")
            },
            {
                println("Failed to store the value $iotDeviceDto")
            }
        )
        return "Your iot device data is $iotDeviceDto"
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Path("/iotdevice")
    @GET
    fun iotDeviceData(): Uni<List<IotDevice>> {
        return iotDeviceDataUseCase.getAllIotDevices()
    }
}
