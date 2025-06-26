package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.evaluarSolicitud

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PantallaEvaluarSolicitudViewModel :ViewModel() {
    private val _uiState = MutableStateFlow(PantallaEvaluarSolicitudUiState() )
    val uiState: StateFlow<PantallaEvaluarSolicitudUiState> = _uiState.asStateFlow()

    fun cambiarVentanaAprobado() {
        var valor: Boolean = _uiState.value.showDialogAprobado
        _uiState.update { currentState -> currentState.copy( showDialogAprobado = !valor ) }
    }

    fun cambiarVentanaDesaprobado() {
        var valor: Boolean = _uiState.value.showDialogDesaprobado
        _uiState.update { currentState -> currentState.copy( showDialogDesaprobado = !valor ) }
    }

    fun asignarAprobacion( aprobado: Boolean ) {
        _uiState.update { currentState -> currentState.copy( aprobado = aprobado ) }
    }
}