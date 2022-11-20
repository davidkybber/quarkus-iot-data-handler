package org.davidcb.iotdatahandler.core.domain.model

import java.time.Instant

data class Temperature(
    val degrees: String,
    val humidity: String,
    val timeStamp: Instant,
)
