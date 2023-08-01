package io.mboettger.teams

import io.mboettger.adaptiveCard.AdaptiveCard

data class WebhookMessageAttachment(
    val content: AdaptiveCard
) {
    val contentType = "application/vnd.microsoft.card.adaptive"
}
