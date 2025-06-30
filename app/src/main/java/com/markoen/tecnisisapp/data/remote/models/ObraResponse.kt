package com.markoen.tecnisisapp.data.remote.models

data class ObraResponse(
    val id: Int,
    val id_tecnica: Int,
    val id_artista: Int,
    val imagen_obra: String,
    val nombre: String,
    val fecha: String,
    val dimensiones: String,
)

data class ObraRequest(
    val id_tecnica: Int,
    val id_artista: Int,
    val imagen_obra: String,
    val nombre: String,
    val fecha: String,
    val dimensiones: String,
)