package com.markoen.tecnisisapp.ui.views.gerente.editarExperto

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
class EditarExpertoViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(EditarExpertoUiState() )
    val uiState: StateFlow<EditarExpertoUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int, id_experto: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil, id_experto = id_experto ) }
    }
    fun actualizarNombre( nombre: String) {
        _uiState.update { currentState -> currentState.copy( nombre = nombre) }
        habilitarBoton()
    }
    fun actualizarCorreo( correo: String) {
        _uiState.update { currentState -> currentState.copy( correo = correo) }
        habilitarBoton()
    }
    fun actualizarContrasena( contrasena: String) {
        _uiState.update { currentState -> currentState.copy( contrasena = contrasena) }
        habilitarBoton()
    }
    fun habilitarBoton() {
        if ( _uiState.value.contrasena != "" && _uiState.value.nombre != "" && _uiState.value.correo != "" ) {
            _uiState.update { currentState -> currentState.copy( habilitadoBoton = true ) }
        }
    }
    fun obtenerExperto() {
        viewModelScope.launch {
            try {
                val experto = usuarioRepository.obtenerUsuario(_uiState.value.id_experto)
                _uiState.update { currentState -> currentState.copy(
                    nombre = experto.nombre,
                    correo = experto.correo,
                    contrasena = experto.contrasena,
                    id_perfil = experto.id_perfil
                ) }
            } catch (e: Exception) {
                Log.d("viewmodelregistrarNuevaTecnica", "entro y agarro al catch")
                Log.d("viewmodelregistrarNuevaTecnica", e.message.toString())
            }
        }
    }
    fun actualizarExperto() {
        viewModelScope.launch {
            try {
                val experto = usuarioRepository.actualizarExperto( _uiState.value.id_experto,_uiState.value.nombre, _uiState.value.correo, _uiState.value.contrasena, 2)
            } catch (e: Exception) {
                Log.d("viewmodelregistrarNuevoExperto", "entro y agarro al catch")
                Log.d("viewmodelregistrarNuevoExperto", e.message.toString())
            }
        }
    }
}