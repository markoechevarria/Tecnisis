package com.markoen.tecnisisapp.data.mapper

import com.markoen.tecnisisapp.data.remote.models.ArtistaResponse
import com.markoen.tecnisisapp.data.remote.models.ObraResponse
import com.markoen.tecnisisapp.data.remote.models.OpcionResponse
import com.markoen.tecnisisapp.data.remote.models.PerfilResponse
import com.markoen.tecnisisapp.data.remote.models.SolicitudResponse
import com.markoen.tecnisisapp.data.remote.models.TecnicaResponse
import com.markoen.tecnisisapp.data.remote.models.UsuarioResponse
import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Opcion
import com.markoen.tecnisisapp.domain.models.Perfil
import com.markoen.tecnisisapp.domain.models.Solicitud
import com.markoen.tecnisisapp.domain.models.Tecnica
import com.markoen.tecnisisapp.domain.models.Usuario

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
        imagen_obra = this.imagen_obra,
        nombre = this.nombre,
        fecha = this.fecha,
        dimensiones = this.dimensiones
    )
}

fun PerfilResponse.toDomain(): Perfil {
    return Perfil(
        id = this.id,
        nombre = this.nombre
    )
}