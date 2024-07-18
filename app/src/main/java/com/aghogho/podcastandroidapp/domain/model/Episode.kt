package com.aghogho.podcastandroidapp.domain.model

data class Episode(
    val id: Long,
    val title: String,
    val description: String,
    val audioUrl: String,
    val duration: Long,
    val publicationDate: String
)
