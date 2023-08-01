package io.mboettger.sentry.event

data class SentryEvent(
    val event_id: String,
    val project: Int,
    val release: String?,
    val dist: Any?,
    val platform: String?,
    val message: String?,
    val datetime: String?,
    val title: String,
    val type: String,
    val url: String,
    val web_url: String,
    val issue_url: String,
)
