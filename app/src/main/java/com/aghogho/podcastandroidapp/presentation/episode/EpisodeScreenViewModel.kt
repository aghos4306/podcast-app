package com.aghogho.podcastandroidapp.presentation.episode

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aghogho.podcastandroidapp.domain.usecases.GetPodcastEpisodeUseCase
import com.aghogho.podcastandroidapp.util.PodcastEpisodePlayer
import com.aghogho.podcastandroidapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EpisodeScreenViewModel @Inject constructor(
    private val getPodcastEpisodeUseCase: GetPodcastEpisodeUseCase
): ViewModel() {

    private val _episodeState = mutableStateOf(EpisodeListState())
    val episodeState: State<EpisodeListState> = _episodeState

    val isPlaying = PodcastEpisodePlayer.isPlaying.stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.Eagerly, false)

    private val _currentlyPlayingEpisodeId = MutableStateFlow<Long?>(null)
    val currentPlayingEpisodeId: StateFlow<Long?> = _currentlyPlayingEpisodeId

    fun loadPodcastEpisodes(podcastId: Long) {
        getPodcastEpisodeUseCase(podcastId, 10, 0).onEach {
            when(it) {
                is Resource.Loading -> {
                    _episodeState.value = EpisodeListState(isLoading = true)
                }
                is Resource.Success -> {
                    _episodeState.value = EpisodeListState(episodeData = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _episodeState.value = EpisodeListState(error = it.message ?: "An Unknown Error Occurred...")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun playEpisode(episodeId: Long, audioUrl: String) {
        PodcastEpisodePlayer.playEpisode(audioUrl)
        _currentlyPlayingEpisodeId.value = episodeId
    }

    fun pauseEpisode() {
        PodcastEpisodePlayer.pauseEpisode()
        _currentlyPlayingEpisodeId.value = null
    }

    override fun onCleared() {
        super.onCleared()
        PodcastEpisodePlayer.release()
    }
}
