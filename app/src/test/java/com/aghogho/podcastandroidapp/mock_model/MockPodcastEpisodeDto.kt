package com.aghogho.podcastandroidapp.mock_model

import com.aghogho.podcastandroidapp.data.remote.podcast_dto.EpisodeDTO
import com.aghogho.podcastandroidapp.data.remote.podcast_dto.PaginationSegmentDTOEpisodeDTO

object MockPodcastEpisodeDto {
    val mockPodcastEpisodeDto = PaginationSegmentDTOEpisodeDTO(
        results = listOf(
            EpisodeDTO(
                id = 1,
                title = "The Daily",
                description = "This is what the news should sound like. The biggest stories of our time, told by the best journalists in the world.",
                audioUrl = "https://www.nytimes.com/the-daily",
                duration = 3600,
                publicationDate = "2024-06-01"
            ),
            EpisodeDTO(
                id = 2,
                title = "Goalhanger Podcasts",
                description = "The world’s most popular history podcast, with Tom Holland and Dominic Sandbrook.\\n\\nJoin The Rest Is History Club (www.therestishistory.com) for ad-free listening to the full archive, weekly bonus episodes",
                audioUrl = "https://feeds.megaphone.fm/GLT4787413333",
                duration = 1800,
                publicationDate = "2024-03-02"
            )
        ),
        limit = 10,
        offset = 0,
        total = 2
    )
}
