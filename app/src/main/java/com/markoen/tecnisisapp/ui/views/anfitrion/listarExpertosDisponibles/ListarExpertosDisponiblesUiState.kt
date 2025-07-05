package com.markoen.tecnisisapp.ui.views.anfitrion.listarExpertosDisponibles

import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Usuario

data class ListarExpertosDisponiblesUiState(
    val id: Int = 0,
    val id_perfil: Int = 0,
    val artista: Artista = Artista(id=0, nombre="",dni="", direccion="", telefono="", ),
    val obra: Obra = Obra(id=0, id_artista=0 , id_tecnica=0, imagen_obra = "", nombre="", fecha="", dimensiones=""),

    val evaluadorArtisticoElegido: Usuario = Usuario(0,0, "","",""),
    val listaEvaluadoresArtisticos: List<Usuario> = emptyList(),

    val expertoSeleccionadoId: Int = -1,
    val habilitadoBotonExpertoSeleccionado: Boolean = false,

    val isLoading: Boolean = true,
    val error: String? = null
)