package com.example.tecnisis.ui.casosDeUso.inicio

import com.example.tecnisis.data.Opcion

data class PantallaInicioUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val opciones: List<Opcion> = emptyList<Opcion>()
)