package com.example.tecnisis.ui.views.login

data class LoginScreenUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val correo: String = "",
    val contrasena: String = "",
    val botonHabilitado: Boolean = false,
    val usuarioRegistrado: Boolean = false
)