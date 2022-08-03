package com.renzj.yinzcam.nfl

import com.renzj.yinzcam.nfl.model.Filter
import com.renzj.yinzcam.nfl.model.GameSection
import com.renzj.yinzcam.nfl.model.Team
import com.renzj.yinzcam.nfl.model.YinzNode
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NflFragmentApiService {
    @GET("/iOS/interviews/ScheduleExercise/schedule.json")
    suspend fun getNflResponseData():NflResponseData?
}

data class NflResponseData(
    val Team: Team,
    val DefaultGameId: String,
    val GameSection: List<GameSection>,
    val Filters: List<Filter>,
    val YinzNode: YinzNode
)