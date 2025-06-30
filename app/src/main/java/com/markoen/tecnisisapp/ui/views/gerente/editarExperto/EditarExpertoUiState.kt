package com.markoen.tecnisisapp.ui.views.gerente.editarExperto

data class EditarExpertoUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val id_experto: Int = 0,
    val nombre: String = "",
    val correo: String = "",
    val contrasena: String = "",
    val habilitadoBoton: Boolean = false
)