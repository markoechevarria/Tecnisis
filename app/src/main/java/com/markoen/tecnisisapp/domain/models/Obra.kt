package com.markoen.tecnisisapp.domain.models

data class Obra (
    val id: Int,
    val id_artista: Int,
    val id_tecnica: Int,
    val imagen_obra: String,
    val nombre: String,
    val fecha: String,
    val dimensiones: String
)