package com.example.tecnisis.ui.casosDeUso.inicio

import com.example.tecnisis.navigation.Rutas

data class PantallaInicioUiState(
    val userType: String? = null,
    val userName: String = "",
    val isLoading: Boolean = false,
    val menuOptions: List<MenuOption> = emptyList()
)

data class MenuOption(
    val title: String,
    val route: String,
    val description: String = ""
) 