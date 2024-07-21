package com.aghogho.podcastandroidapp.presentation.episode.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aghogho.podcastandroidapp.domain.model.Episode

@Composable
fun EpisodeItem(
    episode: Episode,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = episode.title,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        IconButton(onClick = onPlayPauseClick) {
            val icon = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow
            Icon(imageVector = icon, contentDescription = if (isPlaying) "Pause" else "Play")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = episode.description,
            style = MaterialTheme.typography.titleSmall
        )
    }
}
