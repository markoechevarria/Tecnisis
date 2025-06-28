package com.example.tecnisis.ui.views.evaluadorArtistico.evaluarSolicitud

data class PantallaEvaluarSolicitudUiState(
    val id: Int = 0,
    val id_perfil: Int = 0,
    val id_solicitud: Int = 0,
    val aprobado: Boolean = false,
    val showDialogAprobado: Boolean = false,
    val showDialogDesaprobado: Boolean = false
)