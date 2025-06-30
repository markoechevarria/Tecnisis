package com.markoen.tecnisisapp.ui.views.inicio

import com.markoen.tecnisisapp.domain.models.Opcion
import com.markoen.tecnisisapp.domain.models.Perfil
import com.markoen.tecnisisapp.domain.models.Usuario

data class PantallaInicioUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val usuario: Usuario = Usuario(0,0,"","",""),
    val perfil: Perfil = Perfil(0,""),
    val opciones: List<Opcion> = emptyList<Opcion>(),
    val isLoading: Boolean = true,
)