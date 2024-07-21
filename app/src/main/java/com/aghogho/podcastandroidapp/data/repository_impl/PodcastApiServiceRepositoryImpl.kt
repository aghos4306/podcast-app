package com.aghogho.podcastandroidapp.data.repository_impl

import com.aghogho.podcastandroidapp.data.remote.PodcastApiService
import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PaginationSegmentDTOEpisodeDTO
import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PaginationSegmentDTOPodcastDTO
import com.aghogho.podcastandroidapp.domain.repository.PodcastApiServiceRepository
import javax.inject.Inject

class PodcastApiServiceRepositoryImpl @Inject constructor(
    private val podCastApi: PodcastApiService
): PodcastApiServiceRepository {
    override suspend fun getTopPodcasts(limit: Int, offset: Int): PaginationSegmentDTOPodcastDTO {
        return try {
            podCastApi.getTopPodcasts(limit, offset)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPodcastEpisodes(
        podcastId: Long,
        limit: Int,
        offset: Int
    ): PaginationSegmentDTOEpisodeDTO {
        return try {
            podCastApi.getPodcastEpisodes(podcastId, limit, offset)
        } catch (e: Exception) {
            throw e
        }
    }

}
