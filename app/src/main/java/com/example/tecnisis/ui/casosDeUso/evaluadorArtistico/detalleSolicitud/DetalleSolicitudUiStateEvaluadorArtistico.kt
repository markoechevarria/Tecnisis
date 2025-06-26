package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.detalleSolicitud

data class DetalleSolicitudUiStateEvaluadorArtistico  (
    val id_usuario: Int = 0,
    val id_perfil: Int = 0,
    val id_solicitud: Int = 0,

    val dni: String = "",
    val nombre: String = "",
    val direccion: String = "",
    val telefono: String = "",

    val tecnica: String = "",
    val fecha: String = "",
    val dimensiones: String = "",

    val nombreExperto: String = "",

    val estadoSolicitudEconomico: Boolean = false,
)

data class artistaEvaluadorArtistico( val dni: String = "",  val nombre: String = "", val direccion: String = "", val telefono: String = "")
data class obraEvaluadorArtistico(val tecnica: String = "", val fecha: String = "", val dimensiones: String = "", val experto: String = "", val estadoSolicitud: Boolean)