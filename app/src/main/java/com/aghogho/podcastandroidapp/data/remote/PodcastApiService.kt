package com.aghogho.podcastandroidapp.data.remote

import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PaginationSegmentDTOEpisodeDTO
import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PaginationSegmentDTOPodcastDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PodcastApiService {

    @GET("v1/toplist")
    suspend fun getTopPodcasts(@Query("limit") limit: Int, @Query("offset") offset: Int): PaginationSegmentDTOPodcastDTO

    @GET("v1/podcasts/{podcast_id}/episodes")
    suspend fun getPodcastEpisodes(@Path("podcast_id") podcastId: Long, @Query("limit") limit: Int, @Query("offset") offset: Int): PaginationSegmentDTOEpisodeDTO

}
