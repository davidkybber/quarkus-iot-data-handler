package org.davidcb.iotdatahandler.infrastructure

import org.davidcb.iotdatahandler.core.application.IotDataHandlingUseCase
import org.davidcb.iotdatahandler.core.domain.models.Temperature
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/")
class IotDataHandler(
    private val iotDataHandlingUseCase: IotDataHandlingUseCase
) {
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/temp")
    @GET
    fun iotData(@QueryParam("temperature") temp: String?): String {
        iotDataHandlingUseCase.persistIotTemperatureData(Temperature(degrees = temp!!, humidity = null)).subscribe().with(
            {
                println("Stored the value of $temp")
            },
            {
                println("Failed to store the value $temp")
            }
        )
        return "Your temperature is $temp"
    }
}
