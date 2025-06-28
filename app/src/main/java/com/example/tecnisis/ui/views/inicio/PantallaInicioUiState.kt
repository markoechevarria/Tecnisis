package com.example.tecnisis.ui.views.inicio

import com.example.tecnisis.domain.models.Opcion
import com.example.tecnisis.domain.models.Perfil
import com.example.tecnisis.domain.models.Usuario

data class PantallaInicioUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val usuario: Usuario = Usuario(0,0,"","",""),
    val perfil: Perfil = Perfil(0,""),
    val opciones: List<Opcion> = emptyList<Opcion>()
)