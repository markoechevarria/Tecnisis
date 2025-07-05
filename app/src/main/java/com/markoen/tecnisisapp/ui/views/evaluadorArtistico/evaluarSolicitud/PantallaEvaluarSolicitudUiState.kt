package com.markoen.tecnisisapp.ui.views.evaluadorArtistico.evaluarSolicitud

data class PantallaEvaluarSolicitudUiState(
    val id: Int = 0,
    val id_perfil: Int = 0,
    val id_solicitud: Int = 0,
    val url_obra: String = "",
    val aprobado: Boolean = false,
    val showDialogAprobado: Boolean = false,
    val showDialogDesaprobado: Boolean = false,
    val isLoading: Boolean = true
)