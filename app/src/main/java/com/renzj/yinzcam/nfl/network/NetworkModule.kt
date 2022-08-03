package com.renzj.yinzcam.nfl.network

import com.renzj.yinzcam.nfl.NflFragmentApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val TIMEOUT = 60 * 1000.toLong()
        private const val BASE_URL = "http://files.yinzcam.com.s3.amazonaws.com"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL


    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder =
            OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(
                TIMEOUT,
                TimeUnit.SECONDS
            ).writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        return clientBuilder.build()
    }



    @Provides
    @Singleton
    fun providesRetrofitService():Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(providesOkHttpClient())
            .build()


    @Provides
    @Singleton
    fun providesNflApiService(retrofit: Retrofit) =
        retrofit.create(NflFragmentApiService::class.java)

    @Provides
    @Singleton
    fun providesNflApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl
}