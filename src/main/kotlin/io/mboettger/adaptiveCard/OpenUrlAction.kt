package io.mboettger.adaptiveCard

data class OpenUrlAction(
    val title: String,
    val url: String,
) : Action {
    val type = "Action.OpenUrl"
}
