package com.example.tecnisis.ui.views.evaluadorEconomico.detalleSolicitudAprobadaEconomico

data class DetalleSolicitudUiStateAprobadoEconomico  (
    val id_usuario: Int = 0,
    val id_perfil: Int = 0,
    val id_solicitud: Int = 0,

    val dni_artista: String = "",
    val nombre_artista: String = "",
    val direccion: String = "",
    val telefono: String = "",

    val nombre_obra: String = "",
    val tecnica: String = "",
    val fecha: String = "",
    val dimensiones: String = "",

    val nombreExperto: String = "",
    val correo_experto: String = ""
)