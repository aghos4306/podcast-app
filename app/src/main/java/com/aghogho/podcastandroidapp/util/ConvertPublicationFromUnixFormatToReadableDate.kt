package com.aghogho.podcastandroidapp.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertPublicationFromUnixFormatToReadableDate(unixTimeStamp: Long): String {
    val instant = Instant.ofEpochSecond(unixTimeStamp)
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}
