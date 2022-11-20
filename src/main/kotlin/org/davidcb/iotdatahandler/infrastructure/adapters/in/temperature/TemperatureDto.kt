package org.davidcb.iotdatahandler.infrastructure.adapters.`in`.temperature

data class TemperatureDto(
    val iotDeviceId: String,
    val degrees: String,
    val humidity: String,
)
