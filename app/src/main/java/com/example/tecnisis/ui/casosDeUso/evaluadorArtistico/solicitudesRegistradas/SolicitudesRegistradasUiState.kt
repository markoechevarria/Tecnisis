package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesRegistradas

data class SolicitudesRegistradasUiState(
    val solicitudes: List<Solicitud> = emptyList(),
    val solicitudesFiltradas: List<Solicitud> = emptyList(),
    val filtroEstado: String = "TODAS",
    val isLoading: Boolean = true,
    val error: String? = null
)