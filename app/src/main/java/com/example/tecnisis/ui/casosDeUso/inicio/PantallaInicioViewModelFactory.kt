package com.example.tecnisis.ui.casosDeUso.inicio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tecnisis.data.UserPreferences

class PantallaInicioViewModelFactory(
    private val userPreferences: UserPreferences
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PantallaInicioViewModel::class.java)) {
            return PantallaInicioViewModel(userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 