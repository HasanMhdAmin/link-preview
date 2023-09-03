package de.isthasan.inbiolink.model.dto.linkPreview

data class LinkPreviewResponse(
    val domain: String,
    val url: String,
    val title: String,
    val desc: String,
    val image: String,
    val imageAlt: String,
)
