package com.aghogho.podcastandroidapp.util

import com.aghogho.podcastandroidapp.BuildConfig

object ImageUrlUtil {
    fun constructImageUrl(imageId: String?): String? {
        return imageId?.let {
            "${BuildConfig.IMAGE_BASE_URL}$it"
        }
    }
}
