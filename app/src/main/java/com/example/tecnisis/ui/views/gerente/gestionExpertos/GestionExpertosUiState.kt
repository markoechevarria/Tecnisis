package com.example.tecnisis.ui.views.gerente.gestionExpertos

import com.example.tecnisis.domain.models.Usuario

data class GestionExpertosUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val listaExpertos: List<Usuario> = emptyList(),
)