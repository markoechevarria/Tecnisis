package com.markoen.tecnisisapp.data.repository

import com.markoen.tecnisisapp.data.mapper.toDomain
import com.markoen.tecnisisapp.data.remote.InterfazRemoteDataSource
import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Opcion
import com.markoen.tecnisisapp.domain.models.Perfil
import com.markoen.tecnisisapp.domain.models.Solicitud
import com.markoen.tecnisisapp.domain.models.Tecnica
import com.markoen.tecnisisapp.domain.models.Usuario
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsuarioRepositoryImplementacion @Inject constructor(
    private val remoteDataSource: InterfazRemoteDataSource
) : InterfazUsuarioRepository {

    override suspend fun verificarUsuario(correo: String, contrasena: String): Usuario {
        return remoteDataSource.verificarUsuarioRDS( correo= correo, contrasena = contrasena ).toDomain()
    }
    override suspend fun obtenerUsuario(id: Int): Usuario {
        return remoteDataSource.obtenerUsuarioIdRDS( id = id ).toDomain()
    }
    override suspend fun registrarExperto( nombre: String, correo: String, contrasena: String, id_perfil: Int ): Usuario {
        return remoteDataSource.registrarExpertoRDS(nombre, correo, contrasena, id_perfil).toDomain()
    }
    override suspend fun listarEvaluadoresArtisticos(): List<Usuario> {
        return remoteDataSource.listarEvaluadoresArtisticosRDS().map { it.toDomain() }
    }

    override suspend fun obtenerPerfil(id_usuario: Int): Perfil {
        return remoteDataSource.obtenerPerfilRDS(id_usuario).toDomain()
    }

    override suspend fun obtenerOpciones( id_perfil: Int ): List<Opcion> {
        return remoteDataSource.obtenerOpcionesRDS( id_perfil = id_perfil ).map{ it.toDomain() }
    }

    override suspend fun buscarArtistaDni(dni: String): Artista {
        return remoteDataSource.buscarArtistaDniRDS( dni = dni ).toDomain()
    }
    override suspend fun buscarArtistaId(id: Int): Artista {
        return remoteDataSource.buscarArtistaIdRDS( id = id ).toDomain()
    }
    override suspend fun registarArtista(nombre: String, dni: String, direccion: String, telefono: String): Artista {
        return remoteDataSource.registrarArtistaRDS(nombre=nombre,dni=dni,direccion=direccion,telefono=telefono ).toDomain()
    }

    override suspend fun obtenerObra(id: Int): Obra {
        return remoteDataSource.obtenerObraRDS(id = id).toDomain()
    }
    override suspend fun registrarObra(id_tecnica: Int, id_artista: Int, imagen_obra: String, nombre: String, fecha: String, dimensiones: String): Obra {
        return remoteDataSource.registrarObraRDS(id_tecnica, id_artista, imagen_obra, nombre, fecha, dimensiones).toDomain()
    }

    override suspend fun obtenerTecnica(id: Int): Tecnica {
        return remoteDataSource.obtenerTecnicaRDS(id = id).toDomain()
    }
    override suspend fun obtenerTecnicas(): List<Tecnica> {
        return remoteDataSource.obtenerTecnicasRDS().map { it.toDomain() }
    }
    override suspend fun registrarTecnica( nombre_tecnica: String, nivel_apreciacion: String ): Tecnica {
        return remoteDataSource.registrarTecnicaRDS(nombre_tecnica, nivel_apreciacion).toDomain()
    }

    override suspend fun obtenerSolicitudesEvaluadorArtistico(id_evaluador_artistico: Int): List<Solicitud> {
        return remoteDataSource.obtenerSolicitudesEvaluadorArtisticoRDS(id_evaluador_artistico = id_evaluador_artistico).map { it.toDomain() }
    }
    override suspend fun obtenerSolicitudes(): List<Solicitud> {
        return remoteDataSource.obtenerSolicitudesRDS().map { it.toDomain() }
    }
    override suspend fun registrarSolicitud(id_artista: Int, id_obra: Int, id_evaluador_artistico: Int, aprobadaEvaluadorArtistico: Boolean, aprobadaEValuadorEconomico: Boolean, porcentaje_ganancia: Int, precio_venta: Int): Solicitud {
        return remoteDataSource.registrarSolicitudRDS(id_artista, id_obra,id_evaluador_artistico,aprobadaEvaluadorArtistico, aprobadaEValuadorEconomico,porcentaje_ganancia, precio_venta).toDomain()
    }
    override suspend fun obtenerSolicitudPorId(id: Int): Solicitud {
        return remoteDataSource.obtenerSolicitudPorIRDS(id).toDomain()
    }
    override suspend fun evaluarSolicitudArtistico(id: Int, aprobacion: Int): Solicitud {
        return remoteDataSource.evaluarSolicitudArtisticoRDS(id, aprobacion).toDomain()
    }
    override suspend fun asignarPrecios(id: Int, precio: Int, porcentaje: Int): Solicitud {
        return remoteDataSource.asignarPreciosRDS(id, precio, porcentaje).toDomain()
    }

}