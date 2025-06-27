package com.example.tecnisis.domain.models

data class Obra (
    val id: Int,
    val id_artista: Int,
    val id_tecnica: Int,
    val nombre: String,
    val fecha: String,
    val dimensiones: String
)