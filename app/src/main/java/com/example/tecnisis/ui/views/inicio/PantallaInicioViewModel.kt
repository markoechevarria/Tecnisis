package com.example.tecnisis.ui.views.inicio

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
class PantallaInicioViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(PantallaInicioUiState() )
    val uiState: StateFlow<PantallaInicioUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy(id = id, id_perfil = id_perfil) }
        obtenerUsuario()
        obtenerPerfil()
        asignarOpciones()
    }
    fun obtenerUsuario() {
        viewModelScope.launch {
            try {
                val usuario = usuarioRepository.obtenerUsuario(_uiState.value.id)
                _uiState.update { currentState -> currentState.copy( usuario = usuario ) }
            } catch (e: Exception) {
                Log.d("viewmodelpantallainico", "entro y agarro al catch")
                Log.d("viewmodel", e.message.toString())
            }
        }
    }
    fun obtenerPerfil() {
        viewModelScope.launch {
            try {
                val perfil = usuarioRepository.obtenerPerfil(_uiState.value.id)
                _uiState.update { currentState -> currentState.copy( perfil = perfil ) }
            } catch (e: Exception) {
                Log.d("viewmodelpantallainico", "entro y agarro al catch")
                Log.d("viewmodel", e.message.toString())
            }
        }
    }
    fun asignarOpciones() {
        viewModelScope.launch {
            try {
                Log.d("viewmodelpantallainico", _uiState.value.id_perfil.toString())
                Log.d("viewmodelpantallainico", "entrando al try")
                val lista_opciones = usuarioRepository.obtenerOpciones( _uiState.value.id_perfil)
                Log.d("viewmodelpantallainico", "despues de hacer la llamada a la api")
                _uiState.update { currentState -> currentState.copy(
                    opciones = lista_opciones
                ) }
            } catch ( e: Exception ) {
                Log.d("viewmodelpantallainico", "entro y agarro al catch")
                Log.d("viewmodel", e.message.toString())
            }
        }
    }
}