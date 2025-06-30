package com.markoen.tecnisisapp.ui.views.anfitrion.registrarArtista

data class RegistrarArtistaUiState(
    val id: Int = 0,
    val id_perfil: Int = 0,
    val dni: String = "",
    val nombre: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val habilitadoBoton: Boolean = false
)
