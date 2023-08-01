package io.mboettger.adaptiveCard

import com.fasterxml.jackson.annotation.JsonProperty

data class AdaptiveCard(
    val body: List<ContentItem>,
    @JsonProperty("\$schema")
    val schema: String = "http://adaptivecards.io/schemas/adaptive-card.json",
    val version: String = "1.5"
) {
    val type = "AdaptiveCard"
}
