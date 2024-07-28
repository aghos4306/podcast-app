package com.aghogho.podcastandroidapp.presentation.podcast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PodcastHeader(
    modifier: Modifier = Modifier,
    title: String,
    showBackButton: Boolean = false,
    onBackButtonClick: (() -> Unit)? = null,
    backgroundColor: Color = Color.LightGray,
    contentColor: Color = Color.Black
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .padding(16.dp)
    ) {
        if (showBackButton && onBackButtonClick != null) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = onBackButtonClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Podcast",
                    tint = contentColor
                )
            }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = contentColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
