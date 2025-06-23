package com.example.tecnisis.data.mapper

import com.example.tecnisis.data.remote.models.UsuarioResponse
import com.example.tecnisis.domain.models.Usuario

// mapear los modelos de datos de la capa de red/persistencia a modelos de dominio

fun UsuarioResponse.toDomain(): Usuario {
    return Usuario(
        id = this.id,
        nombre = this.nombre,
        correo = this.correo,
        contrasena = this.contrasena,
        id_perfil = this.id_perfil
    )
}