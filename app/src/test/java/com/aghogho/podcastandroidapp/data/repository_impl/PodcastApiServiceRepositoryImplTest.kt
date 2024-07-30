package com.aghogho.podcastandroidapp.data.repository_impl

import com.aghogho.podcastandroidapp.data.remote.PodcastApiService
import com.aghogho.podcastandroidapp.domain.repository.PodcastApiServiceRepository
import com.aghogho.podcastandroidapp.mock_model.MockPodcastDto
import com.aghogho.podcastandroidapp.mock_model.MockPodcastEpisodeDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
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
class PodcastApiServiceRepositoryImplTest {

    @Mock
    private lateinit var podcastApiService: PodcastApiService

    private lateinit var podcastApiServiceRepositoryImpl: PodcastApiServiceRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        podcastApiServiceRepositoryImpl = PodcastApiServiceRepositoryImpl(podcastApiService)
    }

    @Test
    fun `getTopPodcasts should return data when API call is successful`() = runTest {
        val podcastDto = MockPodcastDto.mockPodcastDto

        Mockito.`when`(podcastApiService.getTopPodcasts(10, 0)).thenReturn(podcastDto)

        val result = podcastApiServiceRepositoryImpl.getTopPodcasts(10, 0)

        assertEquals(podcastDto, result)
    }

    @Test
    fun `getTopPodcastEpisode should return data when API call is successful`() = runTest {
        val podcastEpisodeDto = MockPodcastEpisodeDto.mockPodcastEpisodeDto

        Mockito.`when`(podcastApiService.getPodcastEpisodes(1234, 0, 0)).thenReturn(podcastEpisodeDto)

        val result = podcastApiServiceRepositoryImpl.getPodcastEpisodes(1234, 0, 0)

        assertEquals(podcastEpisodeDto, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getTopPodcasts should throw HttpException when API call fails with HTTP error`() = runTest {
        val exception = HttpException(Response.error<String>(404, "".toResponseBody(null)))

        Mockito.`when`(podcastApiService.getTopPodcasts(10, 0)).thenThrow(exception)

        try {
            podcastApiServiceRepositoryImpl.getTopPodcasts(10, 0)
        } catch (e: HttpException) {
            assertEquals(exception.message, e.message)
        }
    }

    @Test
    fun `getTopPodcasts should throw IOException when network error occurs`() = runTest {
        val exception = IOException()

        Mockito.`when`(podcastApiService.getTopPodcasts(10, 0)).thenAnswer { throw exception }

        try {
            podcastApiServiceRepositoryImpl.getTopPodcasts(10, 0)
        } catch (e: IOException) {
            assertEquals(exception.message, e.message)
        }
    }
}
