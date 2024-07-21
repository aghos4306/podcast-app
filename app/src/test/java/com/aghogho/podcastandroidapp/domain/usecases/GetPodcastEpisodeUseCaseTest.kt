package com.aghogho.podcastandroidapp.domain.usecases

import com.aghogho.podcastandroidapp.data.remote.podcast_dto.toEpisode
import com.aghogho.podcastandroidapp.domain.model.Episode
import com.aghogho.podcastandroidapp.domain.repository.PodcastApiServiceRepository
import com.aghogho.podcastandroidapp.mock_model.MockPodcastEpisodeDto
import com.aghogho.podcastandroidapp.util.Resource
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class GetPodcastEpisodeUseCaseTest {

    @Mock
    private lateinit var podcastApiServiceRepository: PodcastApiServiceRepository
    private lateinit var getPodcastEpisodeUseCase: GetPodcastEpisodeUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getPodcastEpisodeUseCase = GetPodcastEpisodeUseCase(podcastApiServiceRepository)
    }

    @Test
    fun `invoke should emit loading and then success when data is fetched successfully`() = runTest {
        val podcastEpisodeDto = MockPodcastEpisodeDto.mockPodcastEpisodeDto

        Mockito.`when`(podcastApiServiceRepository.getPodcastEpisodes(1L, 10, 0)).thenReturn(podcastEpisodeDto)

        val flow = getPodcastEpisodeUseCase(1L, 10, 0)
        val result = flow.toList()

        val expectedEpisodes = podcastEpisodeDto.results.map { it.toEpisode() }
        val successResult = result[1] as Resource.Success<List<Episode>>

        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertEquals(expectedEpisodes, successResult.data)
    }

    @Test
    fun `invoke should emit loading and then error when HttpException is thrown`() = runTest {
        val exception = HttpException(Response.error<String>(404, "".toResponseBody(null)))

        Mockito.`when`(podcastApiServiceRepository.getPodcastEpisodes(1L, 10, 0)).thenThrow(exception)

        val flow = getPodcastEpisodeUseCase(1L, 10, 0)
        val result = flow.toList()

        val errorResult = result[1] as Resource.Error<List<Episode>>

        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertEquals("HTTP 404 Response.error()", errorResult.message)
    }

    @Test
    fun `invoke should emit loading and then error when IOException is thrown`() = runTest {
        val exception = IOException()

        Mockito.`when`(podcastApiServiceRepository.getPodcastEpisodes(1L, 10, 0)).thenAnswer { throw exception }

        val flow = getPodcastEpisodeUseCase(1L, 10, 0)
        val result = flow.toList()

        val errorResult = result[1] as Resource.Error<List<Episode>>

        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertEquals("Error fetching data, check your network...", errorResult.message)
    }

}
