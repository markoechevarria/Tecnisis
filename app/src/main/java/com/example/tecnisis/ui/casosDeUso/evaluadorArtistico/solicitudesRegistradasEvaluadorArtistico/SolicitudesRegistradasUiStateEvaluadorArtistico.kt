package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico

import com.example.tecnisis.domain.models.Solicitud

data class SolicitudesRegistradasUiStateEvaluadorArtistico (
    val idPerfil: Int = 0,
    val id: Int = 0,
    val listaSolicitudes: List<Solicitud> = emptyList<Solicitud>(),
    val solicitudesDatosArtista: MutableList<SolicitudArtista> = mutableListOf()
)

data class SolicitudArtista (
    val id_solicitud: Int,
    val nombre: String = "",
    val fecha: String = "",
    val tecnica: String = ""
)