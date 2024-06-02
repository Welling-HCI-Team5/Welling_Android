package com.example.welling

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch: StateFlow<Boolean> get() = _isFirstLaunch

    fun setFirstLaunchCompleted() {
        viewModelScope.launch {
            _isFirstLaunch.value = false
        }
    }
}
