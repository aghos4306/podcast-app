package com.aghogho.podcastandroidapp.presentation.episode

import com.aghogho.podcastandroidapp.domain.model.Episode

data class EpisodeListState(
    val isLoading: Boolean = false,
    val episodeData: List<Episode> = emptyList(),
    val error: String? = null
)
