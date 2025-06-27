package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.detalleSolicitud

import com.example.tecnisis.domain.models.Solicitud

data class DetalleSolicitudUiStateEvaluadorArtistico  (
    val id_usuario: Int = 0,
    val id_perfil: Int = 0,
    val id_solicitud: Int = 0,

    val solicitudObjeto: Solicitud = Solicitud(0,0,0,0,false,false, 0.0,0.0),

    val dni: String = "",
    val nombre: String = "",
    val direccion: String = "",
    val telefono: String = "",

    val tecnica: String = "",
    val fecha: String = "",
    val dimensiones: String = "",

    val nombreExperto: String = "",

    val estadoSolicitudArtistico: Boolean = false,
    val estadoSolicitudEconomico: Boolean = false,
)