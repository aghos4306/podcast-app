package com.aghogho.podcastandroidapp.presentation.episode.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aghogho.podcastandroidapp.domain.model.Episode

@Composable
fun EpisodeItem(
    episode: Episode,
    isPlaying: Boolean,
    isCurrentEpisode: Boolean,
    onPlayPauseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = episode.title,
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(24.dp))
        ) {
            IconButton(onClick = onPlayPauseClick) {
                val icon = if (isPlaying && isCurrentEpisode) Icons.Default.Pause else Icons.Default.PlayArrow
                Icon(
                    imageVector = icon,
                    contentDescription = if (isPlaying && isCurrentEpisode) "Pause" else "Play",
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = episode.description,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}
