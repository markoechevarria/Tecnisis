package com.example.tecnisis.ui.casosDeUso.login.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoginSuccessful: Boolean = false,
    val idUsuario: String? = null,
    val tipoUsuario: String? = null
) 