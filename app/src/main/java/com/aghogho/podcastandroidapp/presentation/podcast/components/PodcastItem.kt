package com.aghogho.podcastandroidapp.presentation.podcast.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aghogho.podcastandroidapp.domain.model.Podcast
import com.aghogho.podcastandroidapp.util.Constants.IMAGE_BASE_URL
import com.aghogho.podcastandroidapp.util.ImageUrlUtil

@Composable
fun PodcastItem(podcast: Podcast, onClick: (Podcast) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(podcast) }
    ) {
        podcast.image?.let { imageId ->
            val imageUrl = ImageUrlUtil.constructImageUrl(imageId)
            imageUrl?.let {
                PodcastNetworkImage(
                    modifier = Modifier.size(64.dp),
                    imageUrl = it
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = podcast.title, style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp))
            Text(text = podcast.author, style = MaterialTheme.typography.titleSmall)
        }
    }
}
