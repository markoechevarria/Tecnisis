package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.evaluarSolicitud

data class PantallaEvaluarSolicitudUiState(
    val aprobado: Boolean = false,
    val showDialogAprobado: Boolean = false,
    val showDialogDesaprobado: Boolean = false
)