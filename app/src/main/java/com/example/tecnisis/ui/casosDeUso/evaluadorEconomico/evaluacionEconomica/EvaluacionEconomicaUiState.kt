package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.evaluacionEconomica

data class EvaluacionEconomicaUiState (
    val id_perfil: Int = 0,
    val id_usuario: Int = 0,
    val id_solicitud: Int = 0,

    val id_obra: Int = 0,
    val nombre_obra: String = "",
    val tecnica: String = "",
    val fecha: String = "",
    val dimensiones: String = "",

    val id_experto: Int = 0,
    val nombre_experto: String = "",

    val aprobadaEvaluadorArtistico: Boolean = false,
    val aprobadaEvaluadorEconomico: Boolean = false,

    val precioVenta: Int = 0,
    val porcentajeGanancia: Int = 0,

    val aprobado: Boolean = false,
    val showDialogAprobado: Boolean = false
)