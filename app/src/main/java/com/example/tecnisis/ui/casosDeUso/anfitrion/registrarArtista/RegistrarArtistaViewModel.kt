package com.example.tecnisis.ui.casosDeUso.anfitrion.registrarArtista

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistrarArtistaViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(RegistrarArtistaUiState() )
    val uiState: StateFlow<RegistrarArtistaUiState> = _uiState.asStateFlow()

    fun actualizarDni( dni: String) {
        _uiState.update { currentState -> currentState.copy( dni = dni) }
        habilitarBoton()
    }
    fun actualizarNombre( nombre: String) {
        _uiState.update { currentState -> currentState.copy( nombre = nombre) }
        habilitarBoton()
    }
    fun actualizarDireccion( direccion: String) {
        _uiState.update { currentState -> currentState.copy( direccion = direccion) }
        habilitarBoton()
    }
    fun actualizarTelefono( telefono: String) {
        _uiState.update { currentState -> currentState.copy( telefono = telefono) }
        habilitarBoton()
    }
    fun actualizarCorreo( correo: String) {
        _uiState.update { currentState -> currentState.copy( correo = correo) }
        habilitarBoton()
    }
    fun habilitarBoton() {
        if ( _uiState.value.dni != "" &&
             _uiState.value.nombre != "" &&
             _uiState.value.direccion != "" &&
             _uiState.value.telefono != "" &&
             _uiState.value.correo != "" ) {
             _uiState.update { currentState -> currentState.copy( habilitadoBoton = true ) }
        }
    }
}