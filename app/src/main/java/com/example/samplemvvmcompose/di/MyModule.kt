package com.example.samplemvvmcompose.di

import com.example.samplemvvmcompose.network.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {
    @Singleton
    @Provides
    fun provideRetrofitApi(): RetrofitApi {
        // Create Retrofit API object & return
        return RetrofitApi.instance
    }
}