package com.example.tecnisis.data.remote.models

// estructura de lo que se envia

data class UsuarioRequestVerificacion(
    val correo: String,
    val contrasena: String,
)