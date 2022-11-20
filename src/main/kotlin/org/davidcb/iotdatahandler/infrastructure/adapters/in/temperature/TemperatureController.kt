package org.davidcb.iotdatahandler.infrastructure.adapters.`in`.temperature

import io.smallrye.mutiny.Uni
import org.davidcb.iotdatahandler.core.application.TemperatureDataUseCase
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.temperature.TemperatureMapper
import org.davidcb.iotdatahandler.infrastructure.adapters.outward.temperature.TemperatureMapper.toDomain
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class TemperatureController(
    private val temperatureDataUseCase: TemperatureDataUseCase
) {
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/temperature")
    @POST
    fun temperatureData(temperatureDto: TemperatureDto): String {
        temperatureDataUseCase.persistIotTemperatureData(toDomain(temperatureDto)).subscribe().with(
            {
                println("Stored the value of $temperatureDto")
            },
            {
                println("Failed to store the value $temperatureDto")
            }
        )
        return "Your temperature is $temperatureDto"
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Path("/temperature")
    @GET
    fun getLatestTemperatureData(): Uni<TemperatureDto> {
        return temperatureDataUseCase.getLatestTemperatureData().onItem().transform { TemperatureMapper.toDto(it) }
    }
}
