package com.renzj.yinzcam.nfl.network

import com.renzj.yinzcam.nfl.NflFragmentApiService
import com.renzj.yinzcam.nfl.NflResponseData
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val nflFragmentApiService: NflFragmentApiService? ): ApiHelper{

    override suspend fun getNflResponseData(): NflResponseData? = nflFragmentApiService?.getNflResponseData()
}