package com.aghogho.podcastandroidapp.presentation.screen

sealed class Screens(val route: String) {
    data object PodCastScreen: Screens("podcast_screen")
    data object EpisodeScreen: Screens("episode_screen")
}
