package com.markoen.tecnisisapp.ui.views.evaluadorEconomico.listaObrasAprobadas

import com.markoen.tecnisisapp.domain.models.Solicitud

data class PantallaListaObrasAprobadasUiState (
    val idPerfil: Int = 0,
    val id: Int = 0,
    val listaSolicitudes: List<Solicitud> = emptyList<Solicitud>(),
    val solicitudDatosArtista: SolicitudArtistaAprobadaPorEconomico = SolicitudArtistaAprobadaPorEconomico(),
)

data class SolicitudArtistaAprobadaPorEconomico (
    val id_solicitud: Int = 0,
    val nombre: String = "",
    val nombre_artista: String = "",
    val fecha: String = "",
    val tecnica: String = ""
)