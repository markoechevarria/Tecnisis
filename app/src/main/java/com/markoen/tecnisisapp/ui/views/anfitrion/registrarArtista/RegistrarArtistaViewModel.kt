package com.markoen.tecnisisapp.ui.views.anfitrion.registrarArtista

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrarArtistaViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(RegistrarArtistaUiState() )
    val uiState: StateFlow<RegistrarArtistaUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }
    }
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
    fun registrarArtista ()  {
        viewModelScope.launch {
            try {
                val usuarioRegistro = usuarioRepository.registarArtista(
                    nombre = _uiState.value.nombre,
                    dni = _uiState.value.dni,
                    direccion = _uiState.value.direccion,
                    telefono = _uiState.value.telefono
                )
            } catch (e: Exception) {
                Log.d("RegistrarArtistaViewModel", e.message.toString())
            }
        }
    }
    fun habilitarBoton() {
        if ( _uiState.value.dni != "" &&
             _uiState.value.nombre != "" &&
             _uiState.value.direccion != "" &&
             _uiState.value.telefono != "" ) {
             _uiState.update { currentState -> currentState.copy( habilitadoBoton = true ) }
        }
    }
}