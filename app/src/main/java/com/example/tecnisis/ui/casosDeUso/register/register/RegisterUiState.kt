package com.example.tecnisis.ui.casosDeUso.register.register

data class RegisterUiState(
    val nombre: String = "",
    val dni: String = "",
    val telefono: String = "",
    val correo: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRegisterSuccessful: Boolean = false
) 