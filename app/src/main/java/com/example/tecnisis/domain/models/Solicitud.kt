package com.example.tecnisis.domain.models

data class Solicitud (
    val id: Int,
    val id_artista: Int,
    val id_obra: Int,
    val id_evaluador_artistico: Int,
    val aprobadaEvaluadorArtistico: Boolean,
    val aprobadaEValuadorEconomico: Boolean,
    val porcentaje_ganancia: Double,
    val precio_venta: Double
)
