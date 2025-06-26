package com.example.tecnisis.ui.casosDeUso.anfitrion.listarExpertosDisponibles

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListarExpertosDisponiblesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ListarExpertosDisponiblesUiState() )
    val uiState: StateFlow<ListarExpertosDisponiblesUiState> = _uiState.asStateFlow()

    fun elegirExperto( expertoNumero: Int) {
        _uiState.update { currentState -> currentState.copy( expertoSeleccionado = expertoNumero) }
        habilitarBoton()
    }
    fun habilitarBoton() {
        if ( _uiState.value.expertoSeleccionado >= 1) {
            _uiState.update { currentState -> currentState.copy( habilitadoBoton = true ) }
        }
    }
}