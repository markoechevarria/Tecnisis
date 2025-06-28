package com.example.tecnisis.ui.views.gerente.nuevoExperto

data class NuevoExpertoUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val nombre: String = "",
    val correo: String = "",
    val contrasena: String = "",
    val habilitadoBoton: Boolean = false
)