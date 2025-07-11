package com.markoen.tecnisisapp.ui.views.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico

import com.markoen.tecnisisapp.domain.models.Solicitud

data class SolicitudesRegistradasUiStateEvaluadorArtistico (
    val idPerfil: Int = 0,
    val id: Int = 0,
    val listaSolicitudes: List<Solicitud> = emptyList<Solicitud>(),
    val solicitudDatosArtista: SolicitudArtista = SolicitudArtista(),
    val isLoading: Boolean = true
)

data class SolicitudArtista (
    val id_solicitud: Int = 0,
    val nombre: String = "",
    val nombre_artista: String = "",
    val fecha: String = "",
    val tecnica: String = ""
)