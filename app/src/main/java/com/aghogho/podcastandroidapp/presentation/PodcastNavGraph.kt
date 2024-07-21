package com.aghogho.podcastandroidapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aghogho.podcastandroidapp.presentation.episode.EpisodeScreen
import com.aghogho.podcastandroidapp.presentation.podcast.PodCastScreen
import com.aghogho.podcastandroidapp.presentation.screen.Screens

@Composable
fun PodcastNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.PodCastScreen.route
    ) {
        composable(route = Screens.PodCastScreen.route) {
            PodCastScreen(navController = navController)
        }
        composable("episode_screen/{podcastId}") { backStackEntry ->
            val podcastId = backStackEntry.arguments?.getString("podcastId")
            podcastId?.let {
                EpisodeScreen(navController = navController, podcastId = it)
            }
        }
    }
}
