package com.example.tecnisis.ui.casosDeUso.anfitrion.registrarObra

data class RegistrarObraUiState(
    val nombre: String = "",
    val tecnica: String = "",
    val fecha: String = "",
    val dimensiones: String = "",
    val habilitadoBoton: Boolean = false
)
