package com.markoen.tecnisisapp.ui.views.gerente.reporte.ReporteExpertos

import com.markoen.tecnisisapp.domain.models.ExpertoSolicitudesReporte

data class ReporteExpertosUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val ExpertosLista: List<ExpertoSolicitudesReporte> = emptyList()
)