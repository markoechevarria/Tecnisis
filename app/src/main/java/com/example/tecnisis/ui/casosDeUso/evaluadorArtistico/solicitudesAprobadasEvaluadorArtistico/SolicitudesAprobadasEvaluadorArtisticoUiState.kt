package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesAprobadasEvaluadorArtistico

import com.example.tecnisis.domain.models.Solicitud

data class SolicitudesAprobadasEvaluadorArtisticoUiState (
    val idPerfil: Int = 0,
    val id: Int = 0,
    val listaSolicitudes: List<Solicitud> = emptyList<Solicitud>(),
    val solicitudDatosArtista: SolicitudArtistaAprobada = SolicitudArtistaAprobada(),
)

data class SolicitudArtistaAprobada (
    val id_solicitud: Int = 0,
    val nombre: String = "",
    val nombre_artista: String = "",
    val fecha: String = "",
    val tecnica: String = ""
)