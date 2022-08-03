package com.renzj.yinzcam.nfl.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

fun String.toImageUrl(): String {
    return "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_${this}_light.png"
}

@SuppressLint("SimpleDateFormat")
fun String.FormattedDate(): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date: Date? = format.parse(this)
    return "$date".substring(0, 10)
}