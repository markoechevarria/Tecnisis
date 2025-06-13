package com.example.tecnisis.ui.casosDeUso.anfitrion.busquedaArtista

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BusquedaArtistaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BusquedaArtistaUiState() )
    val uiState: StateFlow<BusquedaArtistaUiState> = _uiState.asStateFlow()
    fun actualizarDni( dni: String) {
        _uiState.update { currentState -> currentState.copy( dni = dni) }
        actualizarSeEncontroNombreArtista()
    }
    fun actualizarSeEncontroNombreArtista() {
        if ( _uiState.value.listaArtistas.any { it.first.contains( _uiState.value.dni ) } ) {
            val nombre_encontrado: Pair<String, String>? =
                _uiState.value.listaArtistas.find { it.first == _uiState.value.dni }
            val nombre_: String = nombre_encontrado?.second ?: ""
            if (nombre_ != "") {
                _uiState.update { currentState ->
                    currentState.copy(
                        seEncontro = true,
                        nombreArtista = nombre_
                    )
                }
            }
        }
        habilitarBotones()
    }
    fun habilitarBotones() {
        if ( _uiState.value.seEncontro ) {
            _uiState.update { currentState -> currentState.copy( habilitadoBotonObra = true ) }
        } else {
            _uiState.update { currentState -> currentState.copy( habilitadoBotonArtista = true) }
        }
    }
}