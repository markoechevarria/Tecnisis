package com.markoen.tecnisisapp.data.remote.models

data class TecnicaResponse(
    val id: Int,
    val nombre_tecnica: String,
    val nivel_apreciacion: String
)

data class TecnicaRequest(
    val nombre_tecnica: String,
    val nivel_apreciacion: String
)