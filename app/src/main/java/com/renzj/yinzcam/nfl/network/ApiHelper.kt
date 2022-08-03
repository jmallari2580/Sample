package com.renzj.yinzcam.nfl.network

import com.renzj.yinzcam.nfl.NflResponseData

interface ApiHelper {

    suspend fun getNflResponseData(): NflResponseData?
}