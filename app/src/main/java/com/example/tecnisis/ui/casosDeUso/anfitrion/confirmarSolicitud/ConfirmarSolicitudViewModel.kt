package com.example.tecnisis.ui.casosDeUso.anfitrion.confirmarSolicitud

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConfirmarSolicitudViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(ConfirmarSolicitudUiState())
    val uiState: StateFlow<ConfirmarSolicitudUiState> = _uiState.asStateFlow()
    
    init {
        loadSolicitudData()
    }
    
    private fun loadSolicitudData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            try {
                // TODO: Implementar llamada a API cuando esté disponible
                // Por ahora usamos datos de ejemplo
                _uiState.update { 
                    it.copy(
                        solicitudId = "S001",
                        artistaNombre = "Juan Pérez",
                        tituloObra = "Paisaje Nocturno",
                        tecnica = "Óleo sobre lienzo",
                        fechaSolicitud = "2025-01-15",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = "Error al cargar datos de solicitud: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }
    
    fun confirmarSolicitud() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            try {
                // TODO: Implementar llamada a API para confirmar solicitud
                // Simulamos un delay para mostrar el loading
                kotlinx.coroutines.delay(1000)
                
                _uiState.update { 
                    it.copy(
                        isConfirmacionExitosa = true,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = "Error al confirmar solicitud: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
    
    fun resetConfirmacion() {
        _uiState.update { it.copy(isConfirmacionExitosa = false) }
    }
}