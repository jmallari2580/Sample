package com.renzj.yinzcam.nfl.composables.components.cards.models

data class FinalCardModel(
    val itemId: Int,
    val homeName: String,
    val awayName: String,
    val homeScore: String,
    val awayScore: String,
    val homeImageUrl: String,
    val awayImageUrl: String,
    val gameDate: String,
    val preSeasonNumber: String,
    val gameState: String
    )