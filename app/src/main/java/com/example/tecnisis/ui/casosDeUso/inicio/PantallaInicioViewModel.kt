package com.example.tecnisis.ui.casosDeUso.inicio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.data.UserPreferences
import com.example.tecnisis.navigation.DynamicNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PantallaInicioViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(PantallaInicioUiState())
    val uiState: StateFlow<PantallaInicioUiState> = _uiState.asStateFlow()
    
    init {
        loadUserData()
    }
    
    private fun loadUserData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Observar el tipo de usuario desde UserPreferences(DATASTORE) Y EL collect REACCIONA A LOS CAMBIOS
            userPreferences.userTypeFlow.collect { userType ->
            //OBTIENE OPCIONES DE MENU SEGUN DEL TIPO_USUARIO
                val menuOptions = DynamicNavigation.getMenuOptions(userType)
                val displayName = DynamicNavigation.getDisplayName(userType)
                
                _uiState.update { 
                    it.copy(
                        userType = userType,        // ← TIPO DE USUARIO DE LA BD
                        userName = displayName,     // ← NOMBRE PARA MOSTRAR
                        menuOptions = menuOptions,  // ← MENÚ DINÁMICO
                        isLoading = false
                    )
                }
            }
        }
    }
} 