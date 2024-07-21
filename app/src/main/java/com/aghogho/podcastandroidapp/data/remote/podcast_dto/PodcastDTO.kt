package com.aghogho.podcastandroidapp.data.remote.podcast_dto

import com.aghogho.podcastandroidapp.domain.model.Podcast

data class PodcastDTO(
    val id: Long,
    val original: Boolean,
    val author: String,
    val image: String?,
    val link: String?,
    val rss: String,
    val categoryIDs: List<Int>,
    val title: String,
    val description: String,
    val seasonal: Boolean
)

fun PodcastDTO.toPodCast(): Podcast {
    return Podcast(
        id = id,
        original = original,
        author = author,
        image = image,
        link = link,
        rss = rss,
        categoryIDs = categoryIDs ?: emptyList(),
        title = title,
        description = description,
        seasonal = seasonal
    )
}
