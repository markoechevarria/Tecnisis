package com.example.tecnisis.data.mapper

import com.example.tecnisis.data.remote.models.ArtistaResponse
import com.example.tecnisis.data.remote.models.ObraResponse
import com.example.tecnisis.data.remote.models.OpcionResponse
import com.example.tecnisis.data.remote.models.SolicitudResponse
import com.example.tecnisis.data.remote.models.TecnicaResponse
import com.example.tecnisis.data.remote.models.UsuarioResponse
import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Opcion
import com.example.tecnisis.domain.models.Solicitud
import com.example.tecnisis.domain.models.Tecnica
import com.example.tecnisis.domain.models.Usuario

fun UsuarioResponse.toDomain(): Usuario {
    return Usuario(
        id = this.id,
        nombre = this.nombre,
        correo = this.correo,
        contrasena = this.contrasena,
        id_perfil = this.id_perfil
    )
}

fun OpcionResponse.toDomain(): Opcion {
    return Opcion(
        id = this.id,
        texto = this.texto,
        id_perfil = this.id_perfil
    )
}

fun ArtistaResponse.toDomain(): Artista {
    return Artista(
        id = this.id,
        nombre = this.nombre,
        dni = this.dni,
        direccion = this.direccion,
        telefono = this.telefono
    )
}

fun SolicitudResponse.toDomain(): Solicitud {
    return Solicitud(
        id = this.id,
        id_artista = this.id_artista,
        id_obra = this.id_obra,
        id_evaluador_artistico = this.id_evaluador_artistico,
        aprobadaEvaluadorArtistico = this.aprobadaEvaluadorArtistico,
        aprobadaEValuadorEconomico = this.aprobadaEValuadorEconomico,
        porcentaje_ganancia = this.porcentaje_ganancia,
        precio_venta = this.precio_venta,
    )
}

fun TecnicaResponse.toDomain(): Tecnica {
    return Tecnica(
        id = this.id,
        nombre_tecnica = this.nombre_tecnica,
        nivel_apreciacion = this.nivel_apreciacion
    )
}

fun ObraResponse.toDomain(): Obra {
    return Obra(
        id = this.id,
        id_tecnica = this.id_tecnica,
        id_artista = this.id_artista,
        nombre = this.nombre,
        fecha = this.fecha,
        dimensiones = this.dimensiones
    )
}