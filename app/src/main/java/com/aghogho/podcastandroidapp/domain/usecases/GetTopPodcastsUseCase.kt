package com.aghogho.podcastandroidapp.domain.usecases

import com.aghogho.podcastandroidapp.data.remote.podcast_dto.toPodCast
import com.aghogho.podcastandroidapp.domain.model.Podcast
import com.aghogho.podcastandroidapp.domain.repository.PodcastApiServiceRepository
import com.aghogho.podcastandroidapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTopPodcastsUseCase @Inject constructor(
    private val podcastApiServiceRepository: PodcastApiServiceRepository
) {
    operator fun invoke(limit: Int, offset: Int): Flow<Resource<List<Podcast>>> = flow {
        try {
            emit(Resource.Loading())
            val podcastDto = podcastApiServiceRepository.getTopPodcasts(limit, offset)
            val podCasts = podcastDto.results.map {
                it.toPodCast()
            }
            emit(Resource.Success(podCasts))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred... "))
        } catch (e: IOException) {
            emit(Resource.Error("Error fetching data, check your network..."))
        }
    }
}
