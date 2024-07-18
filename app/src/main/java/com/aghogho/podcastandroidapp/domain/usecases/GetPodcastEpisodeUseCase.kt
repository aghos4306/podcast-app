package com.aghogho.podcastandroidapp.domain.usecases

import com.aghogho.podcastandroidapp.data.remote.podcast_dto.toEpisode
import com.aghogho.podcastandroidapp.domain.model.Episode
import com.aghogho.podcastandroidapp.domain.repository.PodcastApiServiceRepository
import com.aghogho.podcastandroidapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPodcastEpisodeUseCase @Inject constructor(
    private val podcastApiServiceRepository: PodcastApiServiceRepository
) {
    operator fun invoke(podcastId: Long, limit: Int, offset: Int): Flow<Resource<List<Episode>>> = flow {
        try {
           emit(Resource.Loading())
           val podcastEpisodeDto = podcastApiServiceRepository.getPodcastEpisodes(podcastId, limit, offset)
           val podcastEpisode = podcastEpisodeDto.results.map { it.toEpisode() }
           emit(Resource.Success(podcastEpisode))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "UnExpected Error Occurred..."))
        } catch (e: IOException) {
            emit(Resource.Error("Error fetching data, check your network..."))
        }
    }
}
