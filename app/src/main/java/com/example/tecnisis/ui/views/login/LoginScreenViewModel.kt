package com.example.tecnisis.ui.views.login

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
class LoginScreenViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(LoginScreenUiState() )
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()

    fun actualizarCorreo( correo: String) {
        _uiState.update { currentState -> currentState.copy( correo = correo) }
        habiltarBoton()
    }
    fun actualizarContrasena( contrasena: String) {
        _uiState.update { currentState -> currentState.copy( contrasena = contrasena) }
        habiltarBoton()
    }
    fun habiltarBoton() {
        if (_uiState.value.correo != "" && _uiState.value.contrasena != "" ) {
            _uiState.update { currentState -> currentState.copy(botonHabilitado = true) }
        }
    }
    fun IngresarUsuario() {
        viewModelScope.launch {
            try {
                val usuarioRegistrado = usuarioRepository.verificarUsuario( correo = _uiState.value.correo, contrasena = _uiState.value.contrasena )
                _uiState.update { currentState -> currentState.copy(
                    id = usuarioRegistrado.id,
                    id_perfil = usuarioRegistrado.id_perfil,
                    usuarioRegistrado = true
                ) }
            } catch (e: Exception) {
                Log.d("viewmodel", e.message.toString())
            }
        }
    }
}