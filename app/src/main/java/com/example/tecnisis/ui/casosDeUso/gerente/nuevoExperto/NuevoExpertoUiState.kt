package com.example.tecnisis.ui.casosDeUso.gerente.nuevoExperto

data class NuevoExpertoUiState (
    val dni: String = "",
    val nombre: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val correo: String = "",
    val habilitadoBoton: Boolean = false
)