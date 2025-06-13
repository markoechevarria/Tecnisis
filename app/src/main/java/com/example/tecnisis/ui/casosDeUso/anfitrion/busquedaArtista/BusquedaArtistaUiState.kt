package com.example.tecnisis.ui.casosDeUso.anfitrion.busquedaArtista

data class BusquedaArtistaUiState (
    val dni: String = "",
    val nombreArtista: String = "",
    val seEncontro: Boolean = false,
    val habilitadoBotonObra: Boolean= false,
    val habilitadoBotonArtista: Boolean = false,
    val listaArtistas: MutableList< Pair<String, String> > = mutableListOf(
        "11100011" to "Juan",
        "22233322" to "Percy",
        "12345678" to "Marko"
    )
)