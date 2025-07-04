package com.markoen.tecnisisapp.data.remote.models

data class UsuarioResponse(
    val id: Int,
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val id_perfil: Int,
)

data class UsuarioRequest(
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val id_perfil: Int,
)

data class ExpertoSolicitudesReporteResponse(
    val nombre: String,
    val numero_solicitudes: Int
)