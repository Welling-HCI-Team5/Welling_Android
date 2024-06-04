package com.example.welling

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    val selectedCategories = mutableStateListOf<String>()

    val isFirstLaunchCompleted = mutableStateOf(false)
    fun setFirstLaunchCompleted() {
        isFirstLaunchCompleted.value = true
    }

    fun selectCategory(category: String) {
        if (selectedCategories.contains(category)) {
            selectedCategories.remove(category)
        } else {
            selectedCategories.add(category)
        }
    }
}
