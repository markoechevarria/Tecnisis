package com.markoen.tecnisisapp.navigation
import kotlinx.serialization.Serializable

// LOGIN Y PRINCIPAL

@Serializable data object Login
@Serializable data class Inicio(val id: Int, val id_perfil: Int)

// ANFITRION

@Serializable data class BusquedaArtista( val id: Int, val id_perfil: Int)
@Serializable data class RegistrarArtista( val id: Int, val id_perfil: Int  )
@Serializable data class RegistrarObra( val id: Int, val id_perfil: Int, val id_artista: Int )
@Serializable data class ListarExpertos( val id: Int, val id_perfil: Int, val id_artista: Int, val id_obra: Int )
@Serializable data class ConfirmarSolicitud( val id: Int, val id_perfil: Int, val id_artista: Int, val id_obra: Int, val id_evaluador_economico: Int)
@Serializable data class SolicitudExitosa(val id: Int, val id_perfil: Int)
@Serializable data class SolicitudesRegistradasAnfitrion( val id: Int, val id_perfil: Int )

// EVALUADOR ARTISTICO

@Serializable data class SolicitudesRegistradasEvaluadorArtistico( val id: Int, val id_perfil: Int )
@Serializable data class DetalleSolicitudesEvaluadorArtistico( val id_solicitud: Int, val id_usuario: Int, val id_perfil: Int )
@Serializable data class EvaluarSolicitudArtistico( val id_solicitud: Int, val id_usuario: Int, val id_perfil: Int)
@Serializable data class SolicitudesAprobadasEvaluadorArtistico( val id: Int, val id_perfil: Int )

// EVALUADOR ECONOMICO

@Serializable data class SolicitudesRegistradasEvaluadorEconomico( val id: Int, val id_perfil: Int )
@Serializable data class EvaluacionEconomica( val id: Int, val id_perfil: Int, val id_solicitud: Int )
@Serializable data class DetalleAprobadoEconomico( val id_usuario: Int, val id_perfil: Int, val id_solicitud: Int )
@Serializable data class SolicitudesAprobadasEvaluadorEconomico( val id: Int, val id_perfil: Int )

// GERENTE

@Serializable data class VerExpertos( val id: Int, val id_perfil: Int )
@Serializable data class VerTecnicas( val id: Int, val id_perfil: Int )
@Serializable data class VerReportes( val id: Int, val id_perfil: Int )
@Serializable data class NuevoExperto( val id: Int, val id_perfil: Int )
@Serializable data class NuevaTecnica( val id: Int, val id_perfil: Int )
@Serializable data class VerReporte( val id: Int, val id_perfil: Int )