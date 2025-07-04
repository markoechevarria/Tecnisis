package com.markoen.tecnisisapp.ui.views.gerente.reporte.ReporteTecnicas

import com.markoen.tecnisisapp.domain.models.TecnicaReporte

data class ReporteTecnicasUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val tecnicasLista: List<TecnicaReporte> = emptyList()
)