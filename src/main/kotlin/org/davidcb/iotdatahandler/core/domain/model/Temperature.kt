package org.davidcb.iotdatahandler.core.domain.model

import java.time.Instant

data class Temperature(
    val iotDeviceId: String,
    val degrees: String,
    val humidity: String,
    val timeStamp: Instant,
)
