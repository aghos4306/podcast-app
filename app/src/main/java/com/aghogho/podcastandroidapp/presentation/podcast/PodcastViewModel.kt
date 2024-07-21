package com.aghogho.podcastandroidapp.presentation.podcast

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aghogho.podcastandroidapp.domain.usecases.GetTopPodcastsUseCase
import com.aghogho.podcastandroidapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val getTopPodcastUseCase: GetTopPodcastsUseCase
): ViewModel() {

    private val _podcastState = mutableStateOf(PodcastListState())
    val podcastState: State<PodcastListState> = _podcastState

    init {
        loadTopPodcasts(DEFAULT_LIMIT, DEFAULT_OFFSET)
    }

    fun loadTopPodcasts(limit: Int, offset: Int) {
        getTopPodcastUseCase(limit, offset).onEach {
            when(it) {
                is Resource.Loading -> {
                    _podcastState.value = PodcastListState(isLoading = true)
                }
                is Resource.Success -> {
                    _podcastState.value = PodcastListState(podcastData = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _podcastState.value = PodcastListState(error = it.message ?: "An unknown error occurred...")
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        private const val DEFAULT_LIMIT = 10
        private const val DEFAULT_OFFSET = 0
    }
}
