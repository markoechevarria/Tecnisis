package com.markoen.tecnisisapp.ui.views.gerente.editarTecnica

data class EditarTecnicaUiState (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val id_tecnica: Int = 0,
    val nombre_tecnica: String = "",
    val nivel_apreciacion: String = "",
    val habilitadoBoton: Boolean = false
)