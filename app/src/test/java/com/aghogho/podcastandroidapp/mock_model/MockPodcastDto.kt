package com.aghogho.podcastandroidapp.mock_model

import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PaginationSegmentDTOPodcastDTO
import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PodcastDTO

object MockPodcastDto {
    val mockPodcastDto = PaginationSegmentDTOPodcastDTO(
        results = listOf(
            PodcastDTO(
                id = 1,
                title = "The Daily",
                description = "This is what the news should sound like. The biggest stories of our time, told by the best journalists in the world. Hosted by Michael Barbaro and Sabrina Tavernise",
                author = "The New York Times",
                original = false,
                rss = "https://feeds.simplecast.com/54nAGcIl",
                categoryIDs = listOf(1, 2, 3),
                image = "fecafc63-6f75-51cd-abe2-e001cdfe4e40",
                link = "https://www.nytimes.com/the-daily",
                seasonal = false
            ),
            PodcastDTO(
                id = 2,
                title = "The Rest Is History",
                description = "The world’s most popular history podcast, with Tom Holland and Dominic Sandbrook.\\n\\nJoin The Rest Is History Club (www.therestishistory.com)",
                author = "Goalhanger Podcasts",
                original = false,
                rss = "https://feeds.megaphone.fm/GLT4787413333",
                categoryIDs = listOf(4, 5, 6),
                image = "0e22a777-64c3-59e2-899b-02133f96de9b",
                link = "http://therestishistory.com",
                seasonal = false
            )
        ),
        limit = 10,
        offset = 0,
        total = 2
    )
}
