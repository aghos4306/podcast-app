package com.aghogho.podcastandroidapp.domain.repository

import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PaginationSegmentDTOEpisodeDTO
import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PaginationSegmentDTOPodcastDTO

interface PodcastApiServiceRepository {
    suspend fun getTopPodcasts(limit: Int, offset: Int): PaginationSegmentDTOPodcastDTO
    suspend fun getPodcastEpisodes(podcastId: Long, limit: Int, offset: Int): PaginationSegmentDTOEpisodeDTO
}
