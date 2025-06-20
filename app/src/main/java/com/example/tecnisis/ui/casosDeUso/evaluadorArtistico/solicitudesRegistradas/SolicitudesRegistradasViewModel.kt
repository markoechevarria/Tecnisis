package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesRegistradas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SolicitudesRegistradasViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(SolicitudesRegistradasUiState())
    val uiState: StateFlow<SolicitudesRegistradasUiState> = _uiState.asStateFlow()
    
    init {
        loadSolicitudes()
    }
    
    private fun loadSolicitudes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            try {
                // TODO: Implementar llamada a API cuando esté disponible
                val solicitudes = listOf(
                    Solicitud(
                        id = "S001",
                        artistaNombre = "Juan Pérez",
                        tituloObra = "Paisaje Nocturno",
                        fechaSolicitud = "2025-01-15",
                        estado = "PENDIENTE_ARTISTICA"
                    ),
                    Solicitud(
                        id = "S002", 
                        artistaNombre = "María García",
                        tituloObra = "Retrato Abstracto",
                        fechaSolicitud = "2025-01-14",
                        estado = "EN_EVALUACION_ARTISTICA"
                    )
                )
                
                _uiState.update { 
                    it.copy(
                        solicitudes = solicitudes,
                        solicitudesFiltradas = solicitudes,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = "Error al cargar solicitudes: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }
    
    fun filtrarPorEstado(estado: String) {
        val solicitudes = _uiState.value.solicitudes
        
        val solicitudesFiltradas = if (estado == "TODAS") {
            solicitudes
        } else {
            solicitudes.filter { it.estado == estado }
        }
        
        _uiState.update { 
            it.copy(
                filtroEstado = estado,
                solicitudesFiltradas = solicitudesFiltradas
            )
        }
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}