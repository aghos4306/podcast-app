package com.aghogho.podcastandroidapp.util

import com.aghogho.podcastandroidapp.util.Constants.IMAGE_BASE_URL

object ImageUrlUtil {
    fun constructImageUrl(imageId: String?): String? {
        return imageId?.let {
            "$IMAGE_BASE_URL$it"
        }
    }
}
