package com.renzj.yinzcam.nfl.model

data class NflData(
    val DefaultGameId: String,
    val Filters: List<Filter>,
    val GameSection: List<GameSection>,
    val Team: Team,
    val YinzNode: YinzNode
)