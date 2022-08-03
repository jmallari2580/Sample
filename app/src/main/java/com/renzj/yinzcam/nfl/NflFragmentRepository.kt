package com.renzj.yinzcam.nfl

import android.util.Log
import com.renzj.yinzcam.nfl.network.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NflFragmentRepository @Inject constructor(private val apiHelper: ApiHelper) {

   fun getNflResponseData(): Flow<NflResponseData?> = flow{
        emit(apiHelper.getNflResponseData())
        Log.d("nfldata","nfldata repo ${apiHelper.getNflResponseData()}")
    }.flowOn(Dispatchers.IO)

}