package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.detalleSolicitudAprobadaEconomico

data class DetalleSolicitudUiStateAprobadoEconomico  (
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

data class artistaAprobadoEconomico( val dni: String = "",  val nombre: String = "", val direccion: String = "", val telefono: String = "")
data class obraAprobadoEconomico(val tecnica: String = "", val fecha: String = "", val dimensiones: String = "", val experto: String = "", val estadoSolicitud: Boolean)