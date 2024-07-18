package com.aghogho.podcastandroidapp.data.remote.podcast_dto

import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PodcastDTO

data class PaginationSegmentDTOPodcastDTO(
    val results: List<PodcastDTO>,
    val limit: Int,
    val offset: Int,
    val total: Int
)
