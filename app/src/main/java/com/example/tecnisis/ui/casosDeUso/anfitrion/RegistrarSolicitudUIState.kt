package com.example.tecnisis.ui.casosDeUso.anfitrion

import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Usuario

data class RegistrarSolicitudUIState(
    val dni: String = "",
    val nombreArtista: String = "",
    val seEncontro: Boolean = false,
    val habilitadoBotonObra: Boolean= false,
    val habilitadoBotonArtista: Boolean = false,

    val id: Int = 0,
    val id_perfil: Int = 0,
    val artista: Artista = Artista(id=0, nombre="",dni="", direccion="", telefono="", ),
    val obra: Obra = Obra(id=0, id_artista=0 , id_tecnica=0, nombre="", fecha="", dimensiones=""),

    val obra_registrada: Boolean = false,
    val id_obra: Int = 0,
    val id_artista_obra: Int = 0,
    val id_tecnica_obra: Int = 0,
    val nombre_obra: String = "",
    val fecha_obra: String =  "",
    val dimensiones_obra: String = "",

    val evaluadorArtisticoElegido: Usuario = Usuario(0,0, "","",""),
    val listaEvaluadoresArtisticos: List<Usuario> = emptyList(),

    val expertoSeleccionadoId: Int = -1,
    val habilitadoBotonExpertoSeleccionado: Boolean = false
)