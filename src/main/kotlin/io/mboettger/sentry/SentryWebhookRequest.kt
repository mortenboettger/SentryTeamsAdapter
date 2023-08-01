package io.mboettger.sentry

data class SentryWebhookRequest(
    val action: String,
    val installation: SentryInstallation,
    val data: SentryRequestData,
    val actor: SentryActor,
)
