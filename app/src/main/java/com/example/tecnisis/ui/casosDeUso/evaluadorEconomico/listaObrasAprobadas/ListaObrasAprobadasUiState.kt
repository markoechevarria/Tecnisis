package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.listaObrasAprobadas

data class ListaObrasAprobadasUiState(
    val obras: List<ObraAprobada> = emptyList(),
    val obrasFiltradas: List<ObraAprobada> = emptyList(),
    val filtroTecnica: String = "TODAS",
    val isLoading: Boolean = true,
    val error: String? = null
)

data class ObraAprobada(
    val id: String,
    val artistaNombre: String,
    val tituloObra: String,
    val fechaAprobacion: String,
    val tecnica: String
) 