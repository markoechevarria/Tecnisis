package com.markoen.tecnisisapp.domain.models

data class Usuario(
    val id: Int,
    val id_perfil: Int,
    val nombre: String,
    val correo: String,
    val contrasena: String
)

data class ExpertoSolicitudesReporte(
    val nombre: String,
    val numero_solicitudes: Int
)