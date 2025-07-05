package com.markoen.tecnisisapp.ui.views.evaluadorArtistico.solicitudesAprobadasEvaluadorArtistico

import com.markoen.tecnisisapp.domain.models.Solicitud

data class SolicitudesAprobadasEvaluadorArtisticoUiState (
    val idPerfil: Int = 0,
    val id: Int = 0,
    val listaSolicitudes: List<Solicitud> = emptyList<Solicitud>(),
    val solicitudDatosArtista: SolicitudArtistaAprobada = SolicitudArtistaAprobada(),
    val isLoading: Boolean = true
)

data class SolicitudArtistaAprobada (
    val id_solicitud: Int = 0,
    val nombre: String = "",
    val nombre_artista: String = "",
    val fecha: String = "",
    val tecnica: String = ""
)