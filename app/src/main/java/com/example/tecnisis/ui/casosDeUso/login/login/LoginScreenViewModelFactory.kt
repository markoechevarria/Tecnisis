package com.example.tecnisis.ui.casosDeUso.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tecnisis.data.UserPreferences

class LoginScreenViewModelFactory(
    private val userPreferences: UserPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginScreenViewModel(userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 