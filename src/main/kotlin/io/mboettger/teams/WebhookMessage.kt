package io.mboettger.teams

data class WebhookMessage(
    val attachments: List<WebhookMessageAttachment>
) {
    val type = "message"
}
