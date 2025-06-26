package com.example.tecnisis.data.mapper

import com.example.tecnisis.data.remote.models.OpcionResponse
import com.example.tecnisis.data.remote.models.UsuarioResponse
import com.example.tecnisis.domain.models.Opcion
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