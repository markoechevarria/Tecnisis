package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.evaluacionEconomica

data class EvaluacionEconomicaUiState (
    val id_perfil: Int = 0,
    val id_usuario: Int = 0,
    val id_solicitud: Int = 0,

    val id_obra: Int = 0,
    val tecnica: String = "",
    val fecha: String = "",
    val dimensiones: String = "",

    val id_experto: Int = 0,
    val nombre_experto: String = "",

    val aprobadaEvaluadorArtistico: Boolean = false,
    val aprobadaEvaluadorEconomico: Boolean = false,

    val precioVenta: Double = 0.0,
    val porcentajeGanancia: Double = 0.0,

    val aprobado: Boolean = false,
    val showDialogAprobado: Boolean = false
)

data class expertoEvaluadorEconomico( val id_experto: Int = 0,  val nombre_experto: String = "")
data class obraEvaluadorEconomico(val tecnica: String = "", val fecha: String = "", val dimensiones: String = "", val id_obra: Int = 0, val aprobadaEvaluadorArtistico: Boolean, val aprobadaEvaluadorEconomico: Boolean)