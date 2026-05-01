package com.example.samplemvvmcompose.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplemvvmcompose.domain.MyRepository
import com.example.samplemvvmcompose.model.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MainViewModel @Inject constructor (private val repository: MyRepository): ViewModel() { //private val _userState = MutableStateFlow<UserState>(UserState.Loading)

    val userState: StateFlow<UserState> = repository.fetchUserProfile()
        .onStart { UserState.Loading }
        .catch { e -> UserState.Error(e.message ?: "Unknown error") }
        .map { user -> UserState.Success(user) }
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserState.Loading
        )

}