package com.example.samplemvvmcompose.di

import android.util.Log
import com.example.samplemvvmcompose.network.RetrofitApi
import com.example.samplemvvmcompose.network.RetrofitApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Hilt dependency injection module for network-related dependencies.
 *
 * This module provides singleton instances of HTTP clients and Retrofit API service.
 * All providers are singletons to ensure a single shared instance across the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    /**
     * Provides a configured OkHttpClient with logging and timeout settings.
     *
     * The client includes:
     * - HTTP logging interceptor for debugging API calls
     * - 30-second connection timeout to prevent indefinite hangs
     * - 30-second read timeout for response data
     * - 30-second write timeout for request data
     *
     * @return A configured [OkHttpClient] instance
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("OkHttp", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides a configured Retrofit API service instance.
     *
     * The Retrofit instance is configured with:
     * - Custom OkHttpClient with timeouts and logging
     * - Gson converter factory for JSON serialization/deserialization
     * - GitHub API base URL
     *
     * @param okHttpClient The configured [OkHttpClient] for HTTP requests
     * @return A Retrofit API service implementation
     */
    @Singleton
    @Provides
    fun provideRetrofitApi(okHttpClient: OkHttpClient): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }
}