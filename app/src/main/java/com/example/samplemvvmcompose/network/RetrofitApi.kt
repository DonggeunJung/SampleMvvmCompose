package com.example.samplemvvmcompose.network

import com.example.samplemvvmcompose.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitApi {
    @GET("users/DonggeunJung")
    suspend fun user(): User

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        val instance: RetrofitApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitApi::class.java)
    }
}