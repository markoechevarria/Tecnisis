package com.markoen.tecnisisapp.domain.models

data class Artista (
    val id: Int,
    val nombre: String,
    val dni: String,
    val direccion: String,
    val telefono: String
)

data class ArtistaReporte (
    val nombre: String,
    val precio: Int
)