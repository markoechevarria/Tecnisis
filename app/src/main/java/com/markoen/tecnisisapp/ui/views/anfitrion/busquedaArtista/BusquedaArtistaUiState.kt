package com.markoen.tecnisisapp.ui.views.anfitrion.busquedaArtista

data class BusquedaArtistaUiState (
    val id: Int = 0,
    val dni: String = "",
    val nombreArtista: String = "",
    val idArtista: Int = 0,
    val seEncontro: Boolean = false,
    val habilitadoBotonObra: Boolean= false,
    val habilitadoBotonArtista: Boolean = false,
    val cantDigitos: Int = 0
)