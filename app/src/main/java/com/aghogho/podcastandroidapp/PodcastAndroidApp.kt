package com.aghogho.podcastandroidapp

import android.app.Application
import com.aghogho.podcastandroidapp.util.PodcastEpisodePlayer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PodcastAndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
        PodcastEpisodePlayer.initializePlayer(this)
    }
}
