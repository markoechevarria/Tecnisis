package com.example.tecnisis.ui.casosDeUso.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tecnisis.data.Usuario
//import com.example.tecnisis.domain.models.Usuario
import com.example.tecnisis.data.listaUsuarios
// import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel :ViewModel() {
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
    fun IngresarUsuario(){
        val usuarioEncontrado: Usuario? = listaUsuarios.usuarios.find { usuario ->
            usuario.correo == _uiState.value.correo && usuario.contrasena == _uiState.value.contrasena
        }
        if ( usuarioEncontrado != null ) {
            _uiState.update { currentState -> currentState.copy(id = usuarioEncontrado.id, id_perfil = usuarioEncontrado.id_perfil, usuarioRegistrado = true) }
        }
    }
     /*
    fun IngresarUsuario() {
        viewModelScope.launch {
            try {
                val usuarioRegistrado = usuarioRepository.verificarUsuario( correo = _uiState.value.correo, contrasena = _uiState.value.contrasena )
                _uiState.update { currentState -> currentState.copy(
                    id = usuarioRegistrado.id,
                    id_perfil = usuarioRegistrado.id_perfil,
                    usuarioRegistrado = true
                ) }
            } catch (e: Exception) { }
        }
    }*/
}