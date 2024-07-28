package com.aghogho.podcastandroidapp.data.remote.podcast_dto

import com.aghogho.podcastandroidapp.domain.model.Episode
import com.aghogho.podcastandroidapp.util.convertPublicationFromUnixFormatToReadableDate
import com.google.gson.annotations.SerializedName

data class EpisodeDTO(
    val id: Long,
    val title: String,
    val description: String,
    @SerializedName("url")
    val audioUrl: String?,
    val duration: Long,
    @SerializedName("published")
    val publicationDate: String?
)

fun EpisodeDTO.toEpisode(): Episode {
    return Episode(
        id = id,
        title = title,
        description = description,
        audioUrl = audioUrl ?: "No Audio available for this podcast",
        duration = duration,
        //publicationDate = publicationDate ?: "Unknown publication date"
        publicationDate = publicationDate?.let { convertPublicationFromUnixFormatToReadableDate(it.toLong()) } ?: "Unknown publication date"
    )
}
