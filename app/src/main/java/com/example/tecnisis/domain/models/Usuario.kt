package com.example.tecnisis.domain.models

data class Usuario(
    val id: Int,
    val id_perfil: Int,
    val nombre: String,
    val correo: String,
    val contrasena: String
)