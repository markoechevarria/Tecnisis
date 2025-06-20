package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.listaObrasAprobadas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListaObrasAprobadasViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(ListaObrasAprobadasUiState())
    val uiState: StateFlow<ListaObrasAprobadasUiState> = _uiState.asStateFlow()
    
    init {
        loadObrasAprobadas()
    }
    
    private fun loadObrasAprobadas() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            try {
                // TODO: Implementar llamada a API cuando esté disponible
                val obras = listOf(
                    ObraAprobada(
                        id = "O001",
                        artistaNombre = "Juan Pérez",
                        tituloObra = "Paisaje Nocturno",
                        fechaAprobacion = "2025-01-15",
                        tecnica = "Óleo sobre lienzo"
                    ),
                    ObraAprobada(
                        id = "O002",
                        artistaNombre = "María García", 
                        tituloObra = "Retrato Abstracto",
                        fechaAprobacion = "2025-01-14",
                        tecnica = "Acrílico"
                    )
                )
                
                _uiState.update { 
                    it.copy(
                        obras = obras,
                        obrasFiltradas = obras,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = "Error al cargar obras aprobadas: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }
    
    fun filtrarPorTecnica(tecnica: String) {
        val obras = _uiState.value.obras
        
        val obrasFiltradas = if (tecnica == "TODAS") {
            obras
        } else {
            obras.filter { it.tecnica == tecnica }
        }
        
        _uiState.update { 
            it.copy(
                filtroTecnica = tecnica,
                obrasFiltradas = obrasFiltradas
            )
        }
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
} 