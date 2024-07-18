package com.aghogho.podcastandroidapp.domain.model

data class Podcast(
    val id: Long,
    val title: String,
    val description: String,
    val author: String,
    val image: String?,
    val rss: String,
    val link: String?,
    val original: Boolean,
    val seasonal: Boolean,
    val categoryIDs: List<Int>
)
