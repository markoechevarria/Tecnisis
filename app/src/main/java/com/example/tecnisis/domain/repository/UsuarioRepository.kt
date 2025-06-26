package com.example.tecnisis.domain.repository

import com.example.tecnisis.domain.models.Opcion
import com.example.tecnisis.domain.models.Usuario

interface InterfazUsuarioRepository {
    suspend fun verificarUsuario(correo: String, contrasena: String): Usuario
    suspend fun obtenerOpciones(id_perfil: Int): List<Opcion>
}