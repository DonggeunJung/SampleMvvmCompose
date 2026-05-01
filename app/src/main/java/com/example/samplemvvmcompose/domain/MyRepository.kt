package com.example.samplemvvmcompose.domain

import android.util.Log
import com.example.samplemvvmcompose.model.User
import com.example.samplemvvmcompose.network.RetrofitApi
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Repository for accessing GitHub user profile data.
 *
 * This repository handles the communication with the GitHub API through [RetrofitApi]
 * and provides data as reactive [Flow] streams.
 *
 * @param api The Retrofit API service for GitHub API calls
 */
class MyRepository @Inject constructor(private val api: RetrofitApi) {

    /**
     * Fetches the GitHub user profile for the specified username.
     *
     * The data is fetched on the [Dispatchers.IO] thread to avoid blocking the main thread.
     *
     * @param username The GitHub username to fetch the profile for. Defaults to "DonggeunJung".
     * @return A [Flow] emitting the user profile data
     * @throws IllegalArgumentException if the username is blank
     * @throws Exception If the API request fails
     */
    fun fetchUserProfile(username: String = "DonggeunJung"): Flow<User> = flow {
        require(username.isNotBlank()) { "Username cannot be blank" }
        try {
            val response = api.fetchUser(username)
            if(response.isSuccessful) {
                val user = response.body() ?: User()
                emit(user)
            } else {
                throw Exception("Error fetching user profile: Network error!")
            }
        } catch (e: Exception) {
            Log.e("MyRepository", "Error fetching user profile: ${e.message}")
            throw Exception("Error fetching user profile: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)
}