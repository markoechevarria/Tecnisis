package com.example.tecnisis.ui.views.gerente.nuevoExperto

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NuevoExpertoViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {

    private val _uiState = MutableStateFlow(NuevoExpertoUiState() )
    val uiState: StateFlow<NuevoExpertoUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }
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

    fun registrarExperto() {
        viewModelScope.launch {
            try {
                val experto = usuarioRepository.registrarExperto( _uiState.value.nombre, _uiState.value.correo, _uiState.value.contrasena, 2)
            } catch (e: Exception) {
                Log.d("viewmodelregistrarNuevoExperto", "entro y agarro al catch")
                Log.d("viewmodelregistrarNuevoExperto", e.message.toString())
            }
        }
    }
}