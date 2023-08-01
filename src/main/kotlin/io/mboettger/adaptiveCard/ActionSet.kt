package io.mboettger.adaptiveCard

data class ActionSet(
    val actions: List<Action>
) : ContentItem {
    override val type = "ActionSet"
}
