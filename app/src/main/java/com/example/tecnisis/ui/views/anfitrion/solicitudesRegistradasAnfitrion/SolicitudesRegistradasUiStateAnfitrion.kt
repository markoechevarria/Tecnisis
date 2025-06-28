package com.example.tecnisis.ui.views.anfitrion.solicitudesRegistradasAnfitrion

import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Solicitud
import com.example.tecnisis.domain.models.Usuario

data class SolicitudesRegistradasUiStateAnfitrion (
    val listaSolicitudes: List<Solicitud> = emptyList(),
    val obra: Obra = Obra(0,0,0,"","",""),
    val artista: Artista = Artista(0,"","","",""),
    val evaluador_artistico: Usuario = Usuario(0,0,"","","")
)