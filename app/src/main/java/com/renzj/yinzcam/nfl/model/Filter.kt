package com.renzj.yinzcam.nfl.model

data class Filter(
    val Current: String,
    val Items: List<Item>,
    val Name: String,
    val Placeholder: String,
    val QueryParameter: String,
    val Type: String
)