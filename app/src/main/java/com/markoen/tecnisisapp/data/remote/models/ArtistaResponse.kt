package com.markoen.tecnisisapp.data.remote.models

data class ArtistaResponse (
    val id: Int,
    val nombre: String,
    val dni: String,
    val direccion: String,
    val telefono: String
)

data class ArtistaRequest (
    val nombre: String,
    val dni: String,
    val direccion: String,
    val telefono: String
)

data class ArtistaReporteResponse (
    val nombre: String,
    val precio: Int
)