package com.example.tecnisis.data.remote.models

data class SolicitudResponse (
    val id: Int,
    val id_artista: Int,
    val id_obra: Int,
    val id_evaluador_artistico: Int,
    val aprobadaEvaluadorArtistico: Boolean,
    val aprobadaEValuadorEconomico: Boolean,
    val porcentaje_ganancia: Int,
    val precio_venta: Int
)

data class SolicitudRequest (
    val id_artista: Int,
    val id_obra: Int,
    val id_evaluador_artistico: Int,
    val aprobadaEvaluadorArtistico: Boolean,
    val aprobadaEValuadorEconomico: Boolean,
    val porcentaje_ganancia: Int,
    val precio_venta: Int
)