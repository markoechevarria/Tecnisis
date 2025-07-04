package com.markoen.tecnisisapp.domain.models

data class Tecnica (
    val id: Int,
    val nombre_tecnica: String,
    val nivel_apreciacion: String,
)

data class TecnicaReporte(
    val nombre_tecnica: String,
    val numero_obras: Int
)