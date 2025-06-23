package com.example.tecnisis.data.remote.models

// esta es la estructura de la respuesta que vendra de internet

data class UsuarioResponse(
    val id: Int,
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val id_perfil: Int,
)