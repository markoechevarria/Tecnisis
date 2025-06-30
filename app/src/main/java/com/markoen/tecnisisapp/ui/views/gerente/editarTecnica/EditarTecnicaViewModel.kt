package com.markoen.tecnisisapp.ui.views.gerente.editarTecnica

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
class EditarTecnicaViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(EditarTecnicaUiState() )
    val uiState: StateFlow<EditarTecnicaUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int, id_tecnica: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil, id_tecnica = id_tecnica ) }
    }
    fun actualizarNombreTecnica( nombre_tecnica: String) {
        _uiState.update { currentState -> currentState.copy( nombre_tecnica = nombre_tecnica) }
        habilitarBoton()
    }
    fun actualizarNivelAprecicacion( nivel_apreciacion: String) {
        _uiState.update { currentState -> currentState.copy( nivel_apreciacion = nivel_apreciacion) }
        habilitarBoton()
    }
    fun habilitarBoton() {
        if ( _uiState.value.nombre_tecnica != "" && _uiState.value.nivel_apreciacion != "" ) {
            _uiState.update { currentState -> currentState.copy( habilitadoBoton = true ) }
        }
    }
    fun obtenerTecnica() {
        viewModelScope.launch {
            try {
                val tecnica = usuarioRepository.obtenerTecnica(_uiState.value.id_tecnica)
                _uiState.update { currentState -> currentState.copy(
                    nombre_tecnica = tecnica.nombre_tecnica,
                    nivel_apreciacion = tecnica.nivel_apreciacion
                ) }
            } catch (e: Exception) {
                Log.d("viewmodelregistrarNuevaTecnica", "entro y agarro al catch")
                Log.d("viewmodelregistrarNuevaTecnica", e.message.toString())
            }
        }
    }
    fun actualizarTecnica() {
        viewModelScope.launch {
            try {
                val tecnica = usuarioRepository.actualizarTecnica( _uiState.value.id_tecnica,_uiState.value.nombre_tecnica, _uiState.value.nivel_apreciacion)
            } catch (e: Exception) {
                Log.d("viewmodelregistrarNuevaTecnica", "entro y agarro al catch")
                Log.d("viewmodelregistrarNuevaTecnica", e.message.toString())
            }
        }
    }
}