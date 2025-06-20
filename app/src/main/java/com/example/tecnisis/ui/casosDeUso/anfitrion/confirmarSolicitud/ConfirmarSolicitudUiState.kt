package com.example.tecnisis.ui.casosDeUso.anfitrion.confirmarSolicitud

data class ConfirmarSolicitudUiState(
    val solicitudId: String = "",
    val artistaNombre: String = "",
    val tituloObra: String = "",
    val tecnica: String = "",
    val fechaSolicitud: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isConfirmacionExitosa: Boolean = false
)