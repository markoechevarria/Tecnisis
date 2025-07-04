package com.markoen.tecnisisapp.data.remote.services

import com.markoen.tecnisisapp.data.remote.models.ArtistaReporteResponse
import com.markoen.tecnisisapp.data.remote.models.ArtistaRequest
import com.markoen.tecnisisapp.data.remote.models.ArtistaResponse
import com.markoen.tecnisisapp.data.remote.models.ExpertoSolicitudesReporteResponse
import com.markoen.tecnisisapp.data.remote.models.ObraRequest
import com.markoen.tecnisisapp.data.remote.models.ObraResponse
import com.markoen.tecnisisapp.data.remote.models.OpcionResponse
import com.markoen.tecnisisapp.data.remote.models.PerfilResponse
import com.markoen.tecnisisapp.data.remote.models.SolicitudRequest
import com.markoen.tecnisisapp.data.remote.models.SolicitudResponse
import com.markoen.tecnisisapp.data.remote.models.TecnicaReporteResponse
import com.markoen.tecnisisapp.data.remote.models.TecnicaRequest
import com.markoen.tecnisisapp.data.remote.models.TecnicaResponse
import com.markoen.tecnisisapp.data.remote.models.UsuarioRequest
import com.markoen.tecnisisapp.data.remote.models.UsuarioResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("usuarios/ingresar/")
    suspend fun ingresarUsuarioApi( @Query("correo") correo: String, @Query("contrasena") contrasena: String ): Response<UsuarioResponse>
    @GET("usuarios/{usuario_id}")
    suspend fun obtenerUsuarioApi( @Path("usuario_id") usuario_id: Int ): Response<UsuarioResponse>
    @GET("usuarios/evaluadoresArtisticos/")
    suspend fun listarEvaluadoresArtisticosApi(): Response<List<UsuarioResponse>>
    @POST("usuarios/registrarExperto/")
    suspend fun registrarExpertoApi( @Body experto: UsuarioRequest ): Response<UsuarioResponse>
    @PUT("usuarios/actualizar/{id_usuario}")
    suspend fun actualizarExpertoApi( @Path("id_usuario") id_usuario: Int,  @Body usuario: UsuarioRequest ): Response<UsuarioResponse>
    @GET("usuarios/expertoSolicitudes/")
    suspend fun obtenerReporteExpertoSolicitudesApi(): Response<List<ExpertoSolicitudesReporteResponse>>


    @GET("perfil/{id_usuario}")
    suspend fun obtenerPerfil( @Path("id_usuario") id_usuario: Int): Response<PerfilResponse>


    @GET("opciones/{id_perfil}")
    suspend fun obtenerOpcionesApi( @Path("id_perfil") id_perfil: Int ): Response<List<OpcionResponse>>


    @GET("artistas/{dni}")
    suspend fun obtenerArtistaDniApi( @Path("dni") dni: String ): Response<ArtistaResponse>
    @GET("artistas/id/{id}")
    suspend fun obtenerArtistaIdApi( @Path("id") id: Int ): Response<ArtistaResponse>
    @POST("artistas/")
    suspend fun registrarArtistaApi( @Body artista: ArtistaRequest ): Response<ArtistaResponse>
    @GET("artistas/precios/")
    suspend fun obtenerReporteArtistaPreciosApi(): Response<List<ArtistaReporteResponse>>


    @POST("obras/registrar/")
    suspend fun registrarObraApi( @Body obra: ObraRequest ): Response<ObraResponse>
    @GET("obras/id/{id}")
    suspend fun obtenerObraApi( @Path("id") id: Int ): Response<ObraResponse>


    @GET("tecnicas/id/{id}")
    suspend fun obtenerTecnicaApi( @Path("id") id: Int ): Response<TecnicaResponse>
    @GET("tecnicas/")
    suspend fun obtenerTecnicasApi(): Response<List<TecnicaResponse>>
    @POST("tecnicas/registrarTecnica/")
    suspend fun registrarTecnicaApi( @Body tecnica: TecnicaRequest ): Response<TecnicaResponse>
    @PUT("tecnicas/actualizar/{id_tecnica}")
    suspend fun actualizarTecnicaApi( @Path("id_tecnica") id_tecnica: Int,  @Body tecnica: TecnicaRequest ): Response<TecnicaResponse>
    @GET("tecnicas/ObrasPorTecnica/")
    suspend fun obtenerReporteTecnicaApi(): Response<List<TecnicaReporteResponse>>


    @GET("solicitudes/artistico/{id_evaluador_artistico}")
    suspend fun obtenerSolicitudesEvaluadorArtisticoApi( @Path("id_evaluador_artistico") id_evaluador_artistico: Int ): Response<List<SolicitudResponse>>
    @GET("solicitudes/")
    suspend fun obtenerSolicitudesApi(): Response<List<SolicitudResponse>>
    @POST("solicitudes/registrarSolicitud")
    suspend fun registrarSolicitudApi( @Body solicitud: SolicitudRequest ): Response<SolicitudResponse>
    @GET("solicitudes/id/{id}")
    suspend fun obtenerSolicitudPorIdApi( @Path("id") id: Int ): Response<SolicitudResponse>
    @POST("solicitudes/evaluarSolicitud/{id}")
    suspend fun evaluarSolicitudArtisticoApi( @Path("id") solicitud_id: Int,  @Query("aprobacion") aprobacion: Int,  ): Response<SolicitudResponse>
    @POST("solicitudes/asignarPrecio/{id}")
    suspend fun asignarPreciosApi( @Path("id") id: Int,  @Query("precio") precio: Int,  @Query("porcentaje") porcentaje: Int ): Response<SolicitudResponse>
}