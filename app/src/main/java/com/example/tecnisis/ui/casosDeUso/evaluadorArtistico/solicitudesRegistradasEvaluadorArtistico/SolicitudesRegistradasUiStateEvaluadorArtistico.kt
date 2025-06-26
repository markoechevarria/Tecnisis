package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico

data class SolicitudesRegistradasUiStateEvaluadorArtistico (
    val idPerfil: Int = 0,
    val id: Int = 0,
    val listaSolicitudes: List<SolicitudArtista> = emptyList<SolicitudArtista>()
)

data class SolicitudArtista (
    val id_solicitud: Int = 0,
    val id_artista: Int = 0,
    val id_obra: Int = 0,
    val id_experto: Int = 0,
    val aprobadaEvaluadorArtistico: Boolean = false,

    val nombre: String = "",
    val fecha: String = "",
    val tecnica: String = ""
)

data class nombreFechaTecnica ( val nombre: String, val fecha: String, val tecnica: String )