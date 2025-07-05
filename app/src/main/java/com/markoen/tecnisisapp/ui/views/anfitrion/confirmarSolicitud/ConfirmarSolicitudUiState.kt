package com.markoen.tecnisisapp.ui.views.anfitrion.confirmarSolicitud

import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Usuario

data class ConfirmarSolicitudUiState(

    val id: Int = 0,
    val id_perfil: Int = 0,
    val artista: Artista = Artista(id=0, nombre="",dni="", direccion="", telefono="", ),
    val obra: Obra = Obra(id=0, id_artista=0 , id_tecnica=0, imagen_obra = "", nombre="", fecha="", dimensiones=""),

    val evaluadorArtisticoElegido: Usuario = Usuario(0,0, "","",""),

    val fotoObraUrl: String = "",

    val isLoading: Boolean = true,
    val error: String? = null,
)