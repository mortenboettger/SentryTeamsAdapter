package io.mboettger.config

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("adapter")
data class AdapterConfig(
    val teamsWebhookUrl: String,
)
