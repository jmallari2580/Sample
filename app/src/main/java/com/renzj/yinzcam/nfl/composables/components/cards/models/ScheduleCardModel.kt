package com.renzj.yinzcam.nfl.composables.components.cards.models

data class ScheduleCardModel(
    val itemId: Int,
    val homeName: String,
    val awayName: String,
    val homeStanding: String,
    val awayStanding: String,
    val homeImageUrl: String,
    val awayImageUrl: String,
    val gameDate: String,
    val weekNumber: String,
    val gameTime: String,
    val venue: String
)