package com.example.samplemvvmcompose.network

import com.example.samplemvvmcompose.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {
    @GET("users/{username}")
    suspend fun fetchUser(@Path("username") username: String): Response<User>

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}