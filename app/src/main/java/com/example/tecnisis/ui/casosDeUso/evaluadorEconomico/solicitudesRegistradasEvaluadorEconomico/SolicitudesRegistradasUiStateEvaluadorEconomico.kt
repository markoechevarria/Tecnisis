package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.solicitudesRegistradasEvaluadorEconomico

data class SolicitudesRegistradasUiStateEvaluadorEconomico  (
    val idPerfil: Int = 0,
    val id: Int = 0,
    val listaSolicitudes: List<SolicitudArtistaEconomico> = emptyList<SolicitudArtistaEconomico>()
)

data class SolicitudArtistaEconomico (
    val id_solicitud: Int = 0,
    val id_artista: Int = 0,
    val id_obra: Int = 0,
    val id_experto: Int = 0,
    val aprobadaEvaluadorArtistico: Boolean = false,
    val aprobadaEvaluadorEconomico: Boolean = false,
    val nombre: String = "",
    val fecha: String = "",
    val tecnica: String = ""
)

data class nombreFechaTecnicaEconomico ( val nombre: String, val fecha: String, val tecnica: String, val aprobadaEvaluadorArtistico: Boolean, val aprobadaEvaluadorEconomico: Boolean )