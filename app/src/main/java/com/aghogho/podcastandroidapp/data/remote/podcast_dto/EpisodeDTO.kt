package com.aghogho.podcastandroidapp.data.remote.podcast_dto

import com.aghogho.podcastandroidapp.domain.model.Episode

data class EpisodeDTO(
    val id: Long,
    val title: String,
    val description: String,
    val audioUrl: String?,
    val duration: Long,
    val publicationDate: String?
)

fun EpisodeDTO.toEpisode(): Episode {
    return Episode(
        id = id,
        title = title,
        description = description,
        audioUrl = audioUrl ?: "No Audio available for this podcast",
        duration = duration,
        publicationDate = publicationDate ?: "Unknown publication date"
    )
}
