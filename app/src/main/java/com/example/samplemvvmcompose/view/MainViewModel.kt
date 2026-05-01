package com.example.samplemvvmcompose.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplemvvmcompose.domain.MyRepository
import com.example.samplemvvmcompose.model.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor (private val repository: MyRepository): ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> = _userState

    fun fetchUserProfile() {
        _userState.value = UserState.Loading
        viewModelScope.launch {
            repository.fetchUserProfile()
                .catch { exception ->
                    Log.e("MainViewModel", "Error - MyRepository.fetchUserProfile() : ${exception.message}")
                    _userState.value = UserState.Error(exception.message.toString())
                }.collect { user ->
                    _userState.value = UserState.Success(user)
                }
        }
    }
}