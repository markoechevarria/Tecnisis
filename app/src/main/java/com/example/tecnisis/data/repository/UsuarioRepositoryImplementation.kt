package com.example.tecnisis.data.repository

import com.example.tecnisis.data.mapper.toDomain
import com.example.tecnisis.data.remote.InterfazRemoteDataSource
import com.example.tecnisis.domain.models.Usuario
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import javax.inject.Inject
import javax.inject.Singleton

// usa los metodos definidos en la capa de data.remote y los implementa con los metodos de la interfaz domain.repository

@Singleton
class UsuarioRepositoryImplementacion @Inject constructor(
    private val remoteDataSource: InterfazRemoteDataSource
) : InterfazUsuarioRepository {

    override suspend fun verificarUsuario(correo: String, contrasena: String): Usuario {
        return remoteDataSource.verificarUsuario( correo= correo, contrasena = contrasena ).toDomain()
    }
}

// return remoteDataSource.getUsers().map { it.toDomain() }