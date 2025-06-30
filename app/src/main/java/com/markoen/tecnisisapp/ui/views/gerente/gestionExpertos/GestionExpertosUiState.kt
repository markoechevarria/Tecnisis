package com.markoen.tecnisisapp.ui.views.gerente.gestionExpertos

import com.markoen.tecnisisapp.domain.models.Usuario

data class GestionExpertosUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val listaExpertos: List<Usuario> = emptyList(),
)