package io.mboettger.adaptiveCard

data class TextBlock(
    val text: String,
    val wrap: Boolean = true,
    val size: String = "Default",
    val weight: String = "Default",
    val color: String = "Default",
    val isSubtle: Boolean = false,
) : ContentItem {
    override val type = "TextBlock"
}
