package com.example.tecnisis.data.remote

import com.example.tecnisis.data.remote.models.ArtistaRequest
import com.example.tecnisis.data.remote.models.ArtistaResponse
import com.example.tecnisis.data.remote.models.ObraRequest
import com.example.tecnisis.data.remote.models.ObraResponse
import com.example.tecnisis.data.remote.models.OpcionResponse
import com.example.tecnisis.data.remote.models.PerfilResponse
import com.example.tecnisis.data.remote.models.SolicitudRequest
import com.example.tecnisis.data.remote.models.SolicitudResponse
import com.example.tecnisis.data.remote.models.TecnicaRequest
import com.example.tecnisis.data.remote.models.TecnicaResponse
import com.example.tecnisis.data.remote.models.UsuarioRequest
import com.example.tecnisis.data.remote.models.UsuarioResponse
import com.example.tecnisis.data.remote.services.ApiService
import javax.inject.Inject
import javax.inject.Singleton

interface InterfazRemoteDataSource {
    suspend fun verificarUsuarioRDS(correo: String, contrasena: String): UsuarioResponse
    suspend fun obtenerUsuarioIdRDS(id: Int): UsuarioResponse
    suspend fun registrarExpertoRDS(nombre: String, correo: String, contrasena: String, id_perfil: Int ): UsuarioResponse
    suspend fun listarEvaluadoresArtisticosRDS(): List<UsuarioResponse>

    suspend fun obtenerPerfilRDS(id_usuario: Int): PerfilResponse

    suspend fun obtenerOpcionesRDS(id_perfil: Int): List<OpcionResponse>

    suspend fun buscarArtistaDniRDS(dni: String ): ArtistaResponse
    suspend fun buscarArtistaIdRDS(id: Int): ArtistaResponse
    suspend fun registrarArtistaRDS(nombre: String, dni: String, direccion: String, telefono: String ): ArtistaResponse

    suspend fun obtenerObraRDS( id: Int ): ObraResponse
    suspend fun registrarObraRDS(id_tecnica: Int, id_artista: Int, nombre: String, fecha: String, dimensiones: String): ObraResponse

    suspend fun obtenerTecnicaRDS(id: Int): TecnicaResponse
    suspend fun obtenerTecnicasRDS(): List<TecnicaResponse>
    suspend fun registrarTecnicaRDS(nombre_tecnica: String, nivel_apreciacion: String): TecnicaResponse

    suspend fun obtenerSolicitudesEvaluadorArtisticoRDS (id_evaluador_artistico: Int): List<SolicitudResponse>
    suspend fun registrarSolicitudRDS(id_artista: Int, id_obra: Int, id_evaluador_artistico: Int, aprobadaEvaluadorArtistico: Boolean, aprobadaEValuadorEconomico: Boolean, porcentaje_ganancia: Int, precio_venta: Int): SolicitudResponse
    suspend fun obtenerSolicitudesRDS(): List<SolicitudResponse>
    suspend fun obtenerSolicitudPorIRDS(id: Int): SolicitudResponse
    suspend fun evaluarSolicitudArtisticoRDS(id: Int, aprobacion: Int): SolicitudResponse
    suspend fun asignarPreciosRDS(id: Int, precio: Int, porcentaje: Int): SolicitudResponse
}

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : InterfazRemoteDataSource {

    override suspend fun verificarUsuarioRDS(correo: String, contrasena: String): UsuarioResponse {
        val response = apiService.ingresarUsuarioApi( correo = correo, contrasena = contrasena )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al verificar usuario: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun obtenerUsuarioIdRDS(id: Int): UsuarioResponse {
        val response = apiService.obtenerUsuarioApi(usuario_id = id)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener usuario: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun registrarExpertoRDS( nombre: String, correo: String, contrasena: String, id_perfil: Int ): UsuarioResponse {
        val experto = UsuarioRequest(nombre, correo, contrasena, id_perfil )
        val response = apiService.registrarExpertoApi( experto )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al registrar experto: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun listarEvaluadoresArtisticosRDS(): List<UsuarioResponse> {
        val response = apiService.listarEvaluadoresArtisticosApi()
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener obra: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }

    override suspend fun obtenerPerfilRDS(id_usuario: Int): PerfilResponse {
        val response = apiService.obtenerPerfil( id_usuario )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener perfil: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }

    override suspend fun obtenerOpcionesRDS(id_perfil: Int): List<OpcionResponse> {
        val response = apiService.obtenerOpcionesApi( id_perfil = id_perfil )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener opciones: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }

    override suspend fun buscarArtistaDniRDS(dni: String): ArtistaResponse {
        val response = apiService.obtenerArtistaDniApi( dni = dni )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al hallar artista: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun buscarArtistaIdRDS(id: Int): ArtistaResponse {
        val response = apiService.obtenerArtistaIdApi( id = id )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al hallar artista: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun registrarArtistaRDS(nombre: String, dni: String, direccion: String, telefono: String ): ArtistaResponse {
        val artistaRequest: ArtistaRequest = ArtistaRequest(nombre=nombre, dni=dni, direccion=direccion,telefono=telefono)
        val response = apiService.registrarArtistaApi( artistaRequest )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al registrar artista: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }

    override suspend fun obtenerObraRDS(id: Int): ObraResponse {
        val response = apiService.obtenerObraApi(id = id)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener obra: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun registrarObraRDS( id_tecnica: Int, id_artista: Int, nombre: String, fecha: String, dimensiones: String ): ObraResponse {
        val obraRequest = ObraRequest(id_tecnica,id_artista,nombre,fecha,dimensiones)
        val response = apiService.registrarObraApi( obraRequest )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al registrar obra: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }

    override suspend fun registrarTecnicaRDS( nombre_tecnica: String, nivel_apreciacion: String ): TecnicaResponse {
        val tecnica = TecnicaRequest(nombre_tecnica, nivel_apreciacion)
        val response = apiService.registrarTecnicaApi( tecnica )
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al registrar tecnica: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun obtenerTecnicasRDS(): List<TecnicaResponse> {
        val response = apiService.obtenerTecnicasApi()
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener tecnicas: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun obtenerTecnicaRDS(id: Int): TecnicaResponse {
        val response = apiService.obtenerTecnicaApi(id = id)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener tecnica: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }

    override suspend fun asignarPreciosRDS(id: Int, precio: Int, porcentaje: Int): SolicitudResponse {
        val response = apiService.asignarPreciosApi(id, precio, porcentaje)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al asignar precios: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun obtenerSolicitudPorIRDS(id: Int): SolicitudResponse {
        val response = apiService.obtenerSolicitudPorIdApi( id )
        if ( response.isSuccessful && response.body() != null ) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener la solicitud para este id: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun obtenerSolicitudesEvaluadorArtisticoRDS(id_evaluador_artistico: Int): List<SolicitudResponse> {
        val response = apiService.obtenerSolicitudesEvaluadorArtisticoApi( id_evaluador_artistico = id_evaluador_artistico )
        if ( response.isSuccessful && response.body() != null ) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener las solicitudes para el evaluador artistico: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun obtenerSolicitudesRDS(): List<SolicitudResponse> {
        val response = apiService.obtenerSolicitudesApi()
        if ( response.isSuccessful && response.body() != null ) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al obtener las solicitudes: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun registrarSolicitudRDS(id_artista: Int, id_obra: Int, id_evaluador_artistico: Int, aprobadaEvaluadorArtistico: Boolean, aprobadaEValuadorEconomico: Boolean, porcentaje_ganancia: Int, precio_venta: Int): SolicitudResponse {
        val solicitudRequest = SolicitudRequest(id_artista, id_obra,id_evaluador_artistico, aprobadaEvaluadorArtistico, aprobadaEValuadorEconomico, porcentaje_ganancia, precio_venta)
        val response = apiService.registrarSolicitudApi(solicitudRequest)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al registrar solicitud: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
    override suspend fun evaluarSolicitudArtisticoRDS(id: Int, aprobacion: Int): SolicitudResponse {
        val response = apiService.evaluarSolicitudArtisticoApi(id, aprobacion)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "Error al evaluar solictidud artistico: ${response.code()} - $errorBody"
            throw Exception(errorMessage)
        }
    }
}


