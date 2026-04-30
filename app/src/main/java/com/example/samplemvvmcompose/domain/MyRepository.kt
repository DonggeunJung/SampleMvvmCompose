package com.example.samplemvvmcompose.domain

import com.example.samplemvvmcompose.model.User
import com.example.samplemvvmcompose.network.RetrofitApi
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MyRepository @Inject constructor(private val api: RetrofitApi) {

    fun fetchUserProfile(): Flow<User> = flow {
        val user = api.user()
        emit(user)
    }.flowOn(Dispatchers.IO)
}