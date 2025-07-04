package com.markoen.tecnisisapp.ui.views.gerente.reporte.ReporteArtistas

import com.markoen.tecnisisapp.domain.models.ArtistaReporte

data class ReporteArtistasUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val ArtistasLista: List<ArtistaReporte> = emptyList()
)