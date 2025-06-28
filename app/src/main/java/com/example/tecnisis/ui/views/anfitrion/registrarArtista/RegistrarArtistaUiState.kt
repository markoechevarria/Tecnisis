package com.example.tecnisis.ui.views.anfitrion.registrarArtista

data class RegistrarArtistaUiState(
    val dni: String = "",
    val nombre: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val habilitadoBoton: Boolean = false
)
