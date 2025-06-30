package com.markoen.tecnisisapp.ui.views.anfitrion.solicitudesRegistradasAnfitrion

import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Solicitud
import com.markoen.tecnisisapp.domain.models.Usuario

data class SolicitudesRegistradasUiStateAnfitrion (
    val listaSolicitudes: List<Solicitud> = listOf(),
    val listaSolicitudesExtra: MutableList<Pair<Obra,Artista>> = mutableListOf(),
    val obra: Obra = Obra(0,0,0,"","","",""),
    val artista: Artista = Artista(0,"","","",""),
    val evaluador_artistico: Usuario = Usuario(0,0,"","",""),
    val isLoading: Boolean = true
)