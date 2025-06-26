package com.example.tecnisis.data.remote

import com.example.tecnisis.data.mapper.toDomain
import com.example.tecnisis.data.remote.models.OpcionResponse
import com.example.tecnisis.data.remote.models.UsuarioResponse
import com.example.tecnisis.data.remote.services.ApiService
import javax.inject.Inject
import javax.inject.Singleton

interface InterfazRemoteDataSource {
    suspend fun verificarUsuario(correo: String, contrasena: String): UsuarioResponse
    suspend fun obtenerOpciones(id_perfil: Int): List<OpcionResponse>
}

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : InterfazRemoteDataSource {

    override suspend fun verificarUsuario(correo: String, contrasena: String): UsuarioResponse {
        val response = apiService.ingresarUsuario( correo = correo, contrasena = contrasena )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al verificar usuario: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }

    override suspend fun obtenerOpciones(id_perfil: Int): List<OpcionResponse> {
        val response = apiService.obtenerOpciones( id_perfil = id_perfil )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener opciones: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
}