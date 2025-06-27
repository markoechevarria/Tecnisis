package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico

import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.domain.models.Solicitud

data class SolicitudesRegistradasUiStateEvaluadorArtistico (
    val idPerfil: Int = 0,
    val id: Int = 0,
    val listaSolicitudes: List<Solicitud> = emptyList<Solicitud>(),
    val solicitudDatosArtista: SolicitudArtista = SolicitudArtista(),
)

data class SolicitudArtista (
    val id_solicitud: Int = 0,
    val nombre: String = "",
    val nombre_artista: String = "",
    val fecha: String = "",
    val tecnica: String = ""
)