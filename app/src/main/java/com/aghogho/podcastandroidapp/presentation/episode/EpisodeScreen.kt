package com.aghogho.podcastandroidapp.presentation.episode

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aghogho.podcastandroidapp.presentation.episode.component.EpisodeItem
import com.aghogho.podcastandroidapp.presentation.podcast.components.PodcastHeader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EpisodeScreen(
    navController: NavController,
    podcastId: String,
    episodeScreenViewModel: EpisodeScreenViewModel = hiltViewModel()
) {
    val episodeState by episodeScreenViewModel.episodeState
    val isPlaying by episodeScreenViewModel.isPlaying.collectAsState()
    val currentlyPlayingEpisodeId by episodeScreenViewModel.currentPlayingEpisodeId.collectAsState()

    LaunchedEffect(podcastId) {
        episodeScreenViewModel.loadPodcastEpisodes(podcastId.toLong())
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                episodeState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                episodeState.error != null -> {
                    Text(
                        text = episodeState.error!!,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        stickyHeader {
                            PodcastHeader(
                                title = "Episode Details",
                                showBackButton = true,
                                onBackButtonClick = { navController.popBackStack() }
                            )
                        }

                        items(episodeState.episodeData) { episode ->
                            EpisodeItem(
                                episode = episode,
                                isPlaying = isPlaying && currentlyPlayingEpisodeId == episode.id,
                                isCurrentEpisode = currentlyPlayingEpisodeId == episode.id,
                                onPlayPauseClick = {
                                    if (currentlyPlayingEpisodeId == episode.id && isPlaying) {
                                        episodeScreenViewModel.pauseEpisode()
                                    } else {
                                        episodeScreenViewModel.playEpisode(episode.id, episode.audioUrl!!)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
