package com.aghogho.podcastandroidapp.util

import android.content.Context
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

object PodcastEpisodePlayer {
    private var exoPlayer: ExoPlayer? = null
    fun initializePlayer(context: Context) {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build().apply {
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        when (playbackState) {
                            Player.STATE_BUFFERING -> Log.d("EpisodePlayer", "Buffering")
                            Player.STATE_READY -> Log.d("EpisodePlayer", "Ready to Play")
                            Player.STATE_ENDED -> Log.d("EpisodePlayer", "Playback ended")
                            Player.STATE_IDLE-> Log.d("EpisodePlayer", "Player idle")
                        }
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

    fun getPlayer(): ExoPlayer? = exoPlayer
}
