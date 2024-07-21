package com.aghogho.podcastandroidapp.util

import android.content.Context
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object PodcastEpisodePlayer {
    private var exoPlayer: ExoPlayer? = null
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    fun initializePlayer(context: Context) {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build().apply {
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        _isPlaying.value = playbackState == Player.STATE_READY && isPlaying
                        when (playbackState) {
                            Player.STATE_BUFFERING -> Log.d("EpisodePlayer", "Buffering")
                            Player.STATE_READY -> Log.d("EpisodePlayer", "Ready to Play")
                            Player.STATE_ENDED -> Log.d("EpisodePlayer", "Playback ended")
                            Player.STATE_IDLE-> Log.d("EpisodePlayer", "Player idle")
                        }
                    }

                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        super.onIsPlayingChanged(isPlaying)
                        _isPlaying.value = isPlaying
                    }

                    override fun onPlayerError(error: PlaybackException) {
                        super.onPlayerError(error)
                        Log.e("EpisodePlayer", "Player error: ${error.message}")
                    }
                })
            }
        }
    }

    fun release() {
        exoPlayer?.release()
        exoPlayer = null
    }

    fun playEpisode(audioUrl: String) {
        Log.d("EpisodePlayer", "Playing episode with URL: $audioUrl")
        exoPlayer?.apply {
            setMediaItem(MediaItem.fromUri(audioUrl))
            prepare()
            play()
        }
    }

    fun pauseEpisode() {
        exoPlayer?.pause()
    }
}
