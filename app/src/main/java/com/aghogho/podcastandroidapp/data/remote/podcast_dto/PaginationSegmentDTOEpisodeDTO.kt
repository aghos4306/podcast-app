package com.aghogho.podcastandroidapp.data.remote.podcast_dto

data class PaginationSegmentDTOEpisodeDTO(
    val results: List<EpisodeDTO>,
    val total: Int,
    val offset: Int,
    val limit: Int
)
