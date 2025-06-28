package com.example.tecnisis.ui.views.gerente.nuevaTecnica

data class NuevaTecnicaUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val nombre_tecnica: String = "",
    val nivel_apreciacion: String = "",
    val habilitadoBoton: Boolean = false
)