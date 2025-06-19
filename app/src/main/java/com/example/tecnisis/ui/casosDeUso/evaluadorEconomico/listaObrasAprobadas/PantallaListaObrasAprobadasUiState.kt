package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.listaObrasAprobadas

data class PantallaListaObrasAprobadasUiState (
    val idPerfil: Int = 0,
    val id: Int = 0,
    val listaObrasAprobadas: List<obraAprobadaEconomico> = emptyList<obraAprobadaEconomico>()
)

data class obraAprobadaEconomico (
    val id_solicitud: Int = 0,
    val id_artista: Int = 0,
    val id_obra: Int = 0,
    val id_experto: Int = 0,
    val aprobadaEvaluadorArtistico: Boolean = false,
    val aprobadaEvaluadorEconomico: Boolean = false,
    val nombre: String = "",
    val fecha: String = "",
    val tecnica: String = ""
)

data class nombreFechaTecnicaEconomicoAprobadoEconomico ( val nombre: String, val fecha: String, val tecnica: String, val aprobadaEvaluadorArtistico: Boolean, val aprobadaEvaluadorEconomico: Boolean )