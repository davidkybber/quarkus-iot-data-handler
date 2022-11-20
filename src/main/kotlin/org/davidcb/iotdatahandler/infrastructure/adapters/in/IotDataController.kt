package org.davidcb.iotdatahandler.infrastructure.adapters.`in`

import org.davidcb.iotdatahandler.core.application.IotDataHandlingUseCase
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.TemperatureMapper.toDomain
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class IotDataController(
    private val iotDataHandlingUseCase: IotDataHandlingUseCase
) {
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/temperature")
    @POST
    fun iotData(temperatureDto: TemperatureDto): String {
        iotDataHandlingUseCase.persistIotTemperatureData(toDomain(temperatureDto)).subscribe().with(
            {
                println("Stored the value of $temperatureDto")
            },
            {
                println("Failed to store the value $temperatureDto")
            }
        )
        return "Your temperature is $temperatureDto"
    }
}
