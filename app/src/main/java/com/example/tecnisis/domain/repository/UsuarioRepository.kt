package com.example.tecnisis.domain.repository

import com.example.tecnisis.domain.models.Usuario

interface InterfazUsuarioRepository {

    suspend fun verificarUsuario(correo: String, contrasena: String): Usuario

}