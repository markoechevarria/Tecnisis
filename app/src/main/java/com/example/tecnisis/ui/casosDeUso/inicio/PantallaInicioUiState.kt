package com.example.tecnisis.ui.casosDeUso.inicio

import com.example.tecnisis.domain.models.Opcion


data class PantallaInicioUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val opciones: List<Opcion> = emptyList<Opcion>()
)