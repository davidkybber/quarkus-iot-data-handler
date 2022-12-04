package org.davidcb.iotdatahandler.infrastructure.config

import com.google.api.gax.rpc.HeaderProvider
import com.google.cloud.monitoring.v3.MetricServiceSettings
import io.grpc.internal.AbstractManagedChannelImplBuilder
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.stackdriver.StackdriverConfig
import io.micrometer.stackdriver.StackdriverMeterRegistry
import io.quarkus.arc.profile.IfBuildProfile
import javax.enterprise.context.ApplicationScoped
import javax.inject.Singleton
import javax.ws.rs.Produces

@ApplicationScoped
class MicrometerConfig {
    @Produces
    @Singleton
    @IfBuildProfile("prod")
    fun produceMeterRegistry(): MeterRegistry {
        val config = object : StackdriverConfig {
            override fun projectId(): String {
                return "iot-project-368021"
            }

            override fun get(key: String): String? = null
        }
        return StackdriverMeterRegistry
            .builder(config)
            .metricServiceSettings {
                val builder = MetricServiceSettings.newBuilder()
                builder.credentialsProvider = config.credentials()
                builder.transportChannelProvider =
                    MetricServiceSettings.defaultGrpcTransportProviderBuilder().setChannelConfigurator { builder ->
                        when (builder) {
                            is AbstractManagedChannelImplBuilder<*> -> builder.maxInboundMetadataSize(1024 * 1024)
                            else -> builder
                        }
                    }.build()
                builder.headerProvider = HeaderProvider {
                    val version = StackdriverMeterRegistry::class.java.`package`.implementationVersion
                    mapOf(
                        "User-Agent" to "Micrometer/$version micrometer-registry-stackdriver/$version"
                    )
                }
                builder.build()
            }
            .build()
    }
}
