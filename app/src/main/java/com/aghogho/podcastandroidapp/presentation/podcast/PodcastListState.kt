package com.aghogho.podcastandroidapp.presentation.podcast

import com.aghogho.podcastandroidapp.domain.model.Podcast

data class PodcastListState(
    val isLoading: Boolean = false,
    val podcastData: List<Podcast> = emptyList(),
    val error: String? = null
)
