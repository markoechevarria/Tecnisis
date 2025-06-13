package com.example.tecnisis.ui.casosDeUso.anfitrion.registrarArtista

data class RegistrarArtistaUiState(
    val dni: String = "",
    val nombre: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val correo: String = "",
    val habilitadoBoton: Boolean = false
)
