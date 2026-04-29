package com.example.samplemvvmcompose.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplemvvmcompose.domain.MyRepository
import com.example.samplemvvmcompose.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor (private val repository: MyRepository): ViewModel() {
    private val _user = MutableStateFlow<User>(User())
    var user: StateFlow<User> = _user

    fun reqUserProfile() {
        viewModelScope.launch {
            repository.getUserProfile()
                .catch { Log.d("LOG", "Error - repository.getUserProfile()") }
                .collect { user ->
                    _user.value = user
                }
        }
    }
}