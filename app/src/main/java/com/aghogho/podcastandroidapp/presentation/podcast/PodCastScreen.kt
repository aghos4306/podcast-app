package com.aghogho.podcastandroidapp.presentation.podcast

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aghogho.podcastandroidapp.presentation.podcast.components.PodcastHeader
import com.aghogho.podcastandroidapp.presentation.podcast.components.PodcastItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PodCastScreen(
    navController: NavController,
    podcastViewModel: PodcastViewModel = hiltViewModel()
) {
    val podcastState by podcastViewModel.podcastState

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                podcastState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                podcastState.error != null -> {
                    Text(
                        text = podcastState.error!!,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        stickyHeader {
                            PodcastHeader(
                                title = "Podcast Live"
                            )
                        }

                        items(podcastState.podcastData) { podcast ->
                            PodcastItem(podcast = podcast, onClick = {
                                navController.navigate("episode_screen/${podcast.id}")
                            })
                        }
                    }
                }
            }
        }
    }
}

