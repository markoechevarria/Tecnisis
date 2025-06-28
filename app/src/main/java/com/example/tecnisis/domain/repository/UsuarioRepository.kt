package com.example.tecnisis.domain.repository

import com.example.tecnisis.domain.models.Tecnica
import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Opcion
import com.example.tecnisis.domain.models.Perfil
import com.example.tecnisis.domain.models.Solicitud
import com.example.tecnisis.domain.models.Usuario

interface InterfazUsuarioRepository {
    suspend fun verificarUsuario(correo: String, contrasena: String): Usuario
    suspend fun obtenerOpciones(id_perfil: Int): List<Opcion>
    suspend fun buscarArtistaDni(dni: String): Artista
    suspend fun obtenerPerfil(id_usuario: Int): Perfil
    suspend fun buscarArtistaId(id: Int): Artista
    suspend fun registarArtista(nombre: String, dni: String, direccion: String, telefono: String): Artista
    suspend fun obtenerSolicitudesEvaluadorArtistico(id_evaluador_artistico: Int): List<Solicitud>
    suspend fun obtenerObra(id: Int): Obra
    suspend fun obtenerTecnica(id: Int): Tecnica
    suspend fun listarEvaluadoresArtisticos(): List<Usuario>
    suspend fun registrarObra(id_tecnica: Int, id_artista: Int, nombre: String, fecha: String, dimensiones: String): Obra
    suspend fun registrarSolicitud(id_artista: Int, id_obra: Int, id_evaluador_artistico: Int, aprobadaEvaluadorArtistico: Boolean, aprobadaEValuadorEconomico: Boolean, porcentaje_ganancia: Int, precio_venta: Int): Solicitud
    suspend fun obtenerUsuario(id: Int): Usuario
    suspend fun obtenerSolicitudes(): List<Solicitud>
    suspend fun obtenerSolicitudPorId(id: Int): Solicitud
    suspend fun evaluarSolicitudArtistico(id: Int, aprobacion: Int): Solicitud
    suspend fun asignarPrecios(id: Int, precio: Int, porcentaje: Int): Solicitud
    suspend fun obtenerTecnicas(): List<Tecnica>
    suspend fun registrarTecnica(nombre_tecnica: String, nivel_apreciacion: String): Tecnica
    suspend fun registrarExperto(nombre: String, correo: String, contrasena: String, id_perfil: Int): Usuario
}