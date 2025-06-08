package com.treintaYTres.vdc.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.treintaYTres.vdc.BuildConfig
import com.treintaYTres.vdc.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun gson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun retrofit(gson: Gson) = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun apiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

}