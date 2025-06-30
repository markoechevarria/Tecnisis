package com.markoen.tecnisisapp.ui.views.gerente.gestionTecnicas

import com.markoen.tecnisisapp.domain.models.Tecnica

data class GestionTecnicaUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val listaTecnica: List<Tecnica> = emptyList(),
)