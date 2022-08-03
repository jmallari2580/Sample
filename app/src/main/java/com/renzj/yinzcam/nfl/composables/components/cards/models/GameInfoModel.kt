package com.renzj.yinzcam.nfl.composables.components.cards.models

data class GameInfoModel(
    val itemId: Int?,
    val itemType: String,
    val homeName: String,
    val homeStanding: String,
    val triCodeHomeImageUrl: String,
    val homeScore: String,
    val awayName: String,
    val awayStanding: String,
    val triCodeAwayImageUrl: String,
    val awayScore: String,
    val gameDayDate: String,
    val gameTime: String,
    val seasonType: String,
    val seasonWeekNumber: String,
    val venue: String,
    val gameState: String
)