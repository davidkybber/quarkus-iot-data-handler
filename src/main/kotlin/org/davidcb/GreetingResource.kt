package org.davidcb

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/")
class GreetingResource {
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello from RESTEasy Reactive"

    @Path("/test")
    @GET
    fun iotData(@QueryParam("temperature") temp: String?): String {
        return "Your temperature is $temp"
    }
}
