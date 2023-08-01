package io.mboettger.sentry

import io.mboettger.sentry.event.SentryEvent

data class SentryRequestData(
    val event: SentryEvent,
    val triggered_rule: String,
)
