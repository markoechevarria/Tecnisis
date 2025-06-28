package com.example.tecnisis.ui.views.gerente.gestionTecnicas

import com.example.tecnisis.domain.models.Tecnica

data class GestionTecnicaUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val listaTecnica: List<Tecnica> = emptyList(),
)