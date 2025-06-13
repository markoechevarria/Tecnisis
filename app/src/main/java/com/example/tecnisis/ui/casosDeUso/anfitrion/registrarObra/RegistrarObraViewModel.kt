package com.example.tecnisis.ui.casosDeUso.anfitrion.registrarObra

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistrarObraViewModel: ViewModel() {
    private val _uiState = MutableStateFlow( RegistrarObraUiState() )
    val uiState: StateFlow<RegistrarObraUiState> = _uiState.asStateFlow()

    fun actualizarNombre( nombre: String) {
        _uiState.update { currentState -> currentState.copy( nombre = nombre) }
        habilitarBoton()
    }
    fun actualizarFecha( fecha: String) {
        _uiState.update { currentState -> currentState.copy( fecha = fecha) }
        habilitarBoton()
    }
    fun actualizarTecnica( tecnica: String) {
        _uiState.update { currentState -> currentState.copy( tecnica = tecnica) }
        habilitarBoton()
    }
    fun actualizarDimensiones( dimensiones: String) {
        _uiState.update { currentState -> currentState.copy( dimensiones = dimensiones) }
        habilitarBoton()
    }
    fun habilitarBoton() {
        if ( _uiState.value.nombre != "" && _uiState.value.fecha != "" && _uiState.value.dimensiones != "" && _uiState.value.tecnica != "" ) {
            _uiState.update { currentState -> currentState.copy( habilitadoBoton = true ) }
        }
    }
}