package io.mboettger

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.call
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.mboettger.adaptiveCard.OpenUrlAction
import io.mboettger.adaptiveCard.ActionSet
import io.mboettger.adaptiveCard.AdaptiveCard
import io.mboettger.adaptiveCard.TextBlock
import io.mboettger.config.AdapterConfig
import io.mboettger.sentry.SentryWebhookRequest
import io.mboettger.teams.WebhookMessage
import io.mboettger.teams.WebhookMessageAttachment
import jakarta.inject.Singleton
import io.micronaut.ktor.KtorRoutingBuilder

@Singleton
class HomeRoute(private val adapterConfig: AdapterConfig) : KtorRoutingBuilder({
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            jackson()
        }
    }

    post("/") {
        val event = call.receive<SentryWebhookRequest>().data.event
        val card = AdaptiveCard(listOf(
            TextBlock(
                text = event.type.uppercase(),
                size = "Large",
                weight = "Bolder",
                color = "Warning",
            ),
            TextBlock(
                text = event.title,
                weight = "Bolder"
            ),
            TextBlock(
                text = event.message ?: ""
            ),
            ActionSet(listOf(
                OpenUrlAction(
                    title = "Open Event in Browser",
                    url = event.web_url
                )
            ))
        ))

        val response = client.post(adapterConfig.teamsWebhookUrl) {
            contentType(ContentType.Application.Json)
            setBody(WebhookMessage(listOf(WebhookMessageAttachment(card))))
        }

        call.respond("")
    }
})
