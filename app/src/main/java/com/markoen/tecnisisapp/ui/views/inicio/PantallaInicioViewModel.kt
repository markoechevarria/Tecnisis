package com.markoen.tecnisisapp.ui.views.inicio

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
class PantallaInicioViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(PantallaInicioUiState() )
    val uiState: StateFlow<PantallaInicioUiState> = _uiState.asStateFlow()

    fun cargarDatos(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy(id = id, id_perfil = id_perfil) }
        viewModelScope.launch {
            try {
                val usuario = usuarioRepository.obtenerUsuario(_uiState.value.id)
                val perfil = usuarioRepository.obtenerPerfil(_uiState.value.id)
                val lista_opciones = usuarioRepository.obtenerOpciones( _uiState.value.id_perfil)
                _uiState.update { currentState -> currentState.copy( usuario = usuario, perfil = perfil, opciones = lista_opciones ) }
            } catch (e: Exception) {
                Log.d("viewmodelpantallainico", e.message.toString())
            } finally {
                _uiState.update { currentState -> currentState.copy(isLoading = false) }
            }
        }
    }
}