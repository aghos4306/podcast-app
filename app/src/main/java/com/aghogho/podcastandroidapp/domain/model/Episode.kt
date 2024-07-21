package com.aghogho.podcastandroidapp.domain.model

import com.google.gson.annotations.SerializedName

data class Episode(
    val id: Long,
    val title: String,
    val description: String,
    @SerializedName("url")
    val audioUrl: String?,
    val duration: Long,
    val publicationDate: String
)
