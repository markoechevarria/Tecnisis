package com.example.tecnisis.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tecnisis.ui.casosDeUso.anfitrion.busquedaArtista.PantallaBusquedaArtista
import com.example.tecnisis.ui.casosDeUso.anfitrion.registrarArtista.PantallaRegistrarArtista
import com.example.tecnisis.ui.casosDeUso.anfitrion.solicitudesRegistradasAnfitrion.PantallaSolicitudesRegistradasAnfitrion
import com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.detalleSolicitud.PantallaDetalleSolicitudEvaluadorArtistico
import com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.evaluarSolicitud.PantallaEvaluarSolicitud
import com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesAprobadasEvaluadorArtistico.PantallaSolicitudesAprobadasEvaluadorArtistico
import com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico.PantallaSolicitudesRegistradasEvaluadorArtistico
import com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.detalleSolicitudAprobadaEconomico.PantallaDetalleSolicitudAprobadoEconomico
import com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.evaluacionEconomica.PantallaEvaluacionEconomica
import com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.listaObrasAprobadas.PantallaListarObrasAprobadas
import com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.solicitudesRegistradasEvaluadorEconomico.PantallaSolicitudesRegistradasEvaluadorEconomico
import com.example.tecnisis.ui.casosDeUso.gerente.dashboardReportes.PantallaDashboardReportes
import com.example.tecnisis.ui.casosDeUso.gerente.gestionExpertos.PantallaGestionExpertos
import com.example.tecnisis.ui.casosDeUso.gerente.gestionTecnicas.PantallaGestionTecnicas
import com.example.tecnisis.ui.casosDeUso.inicio.PantallaInicio
import com.example.tecnisis.ui.casosDeUso.login.LoginScreen

@Composable
fun NavigationApp () {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Login) {
        // LOGIN E INICIO (PARA TODOS)
        composable<Login> {
            LoginScreen (navigateToPrincipal = { id1, id2 -> navController.navigate(route = Inicio(id = id1, id_perfil = id2 ) ) } )
        }
        composable<Inicio> {
            val inicio : Inicio = it.toRoute()
            PantallaInicio( id = inicio.id, id_perfil = inicio.id_perfil , volverInicio = {
                navController.navigate(route = Login)
            }, navegarOpcion = { id, idPerfil ->
                when (id) {
                    1 -> {navController.navigate( route = BusquedaArtista( id = inicio.id, id_perfil = inicio.id_perfil ) )}
                    2 -> {navController.navigate( route = RegistrarArtista( id = inicio.id, id_perfil = inicio.id_perfil ) )}
                    3 -> {navController.navigate( route = SolicitudesRegistradasAnfitrion( id = inicio.id, id_perfil = inicio.id_perfil ) )}

                    4 -> {navController.navigate( route = SolicitudesRegistradasEvaluadorArtistico( id = inicio.id, id_perfil = inicio.id_perfil ) )}
                    5 -> {navController.navigate( route = SolicitudesAprobadasEvaluadorArtistico( id = inicio.id, id_perfil = inicio.id_perfil ) )}

                    6 -> {navController.navigate( route = SolicitudesRegistradasEvaluadorEconomico( id = inicio.id, id_perfil = inicio.id_perfil ) )}
                    7 -> {navController.navigate( route = SolicitudesAprobadasEvaluadorEconomico( id = inicio.id, id_perfil = inicio.id_perfil ) )}

                    8 -> {navController.navigate( route = VerExpertos( id = inicio.id, id_perfil = inicio.id_perfil ) )}
                    9 -> {navController.navigate( route = VerTecnicas( id = inicio.id, id_perfil = inicio.id_perfil ) )}
                    10 -> {navController.navigate( route = VerReportes( id = inicio.id, id_perfil = inicio.id_perfil ) )}

                    else -> {  }
                }
            })
        }

        // ANFITRION
        composable<BusquedaArtista> {
            val busquedaArtista: BusquedaArtista = it.toRoute()
            PantallaBusquedaArtista(id = busquedaArtista.id, id_perfil = busquedaArtista.id_perfil )
        }
        composable<RegistrarArtista> {
            val registrarArtista: RegistrarArtista = it.toRoute()
            PantallaRegistrarArtista(id = registrarArtista.id, id_perfil = registrarArtista.id_perfil )
        }
        composable<SolicitudesRegistradasAnfitrion> {
            val solicitudesRegistradasAnfitrion: SolicitudesRegistradasAnfitrion = it.toRoute()
            PantallaSolicitudesRegistradasAnfitrion(id = solicitudesRegistradasAnfitrion.id, id_perfil = solicitudesRegistradasAnfitrion.id_perfil )
        }

        // EVALUADOR ARTISTICO
        composable<SolicitudesRegistradasEvaluadorArtistico> {
            val solicitudesRegistradasEvaluadorArtistico: SolicitudesRegistradasEvaluadorArtistico = it.toRoute()
            PantallaSolicitudesRegistradasEvaluadorArtistico( verDetalleSolicitud = { id1, id2, id3 -> navController.navigate(route = DetalleSolicitudesEvaluadorArtistico(id_solicitud=id1, id_usuario=id2, id_perfil=id3) ) } , id = solicitudesRegistradasEvaluadorArtistico.id, id_perfil = solicitudesRegistradasEvaluadorArtistico.id_perfil )
        }
        composable<DetalleSolicitudesEvaluadorArtistico> {
            val detalleSolicitudesEvaluadorArtistico: DetalleSolicitudesEvaluadorArtistico = it.toRoute()
            PantallaDetalleSolicitudEvaluadorArtistico( id_solicitud = detalleSolicitudesEvaluadorArtistico.id_solicitud, id_usuario = detalleSolicitudesEvaluadorArtistico.id_usuario, id_perfil = detalleSolicitudesEvaluadorArtistico.id_perfil, navegarEvaluacion = { id1, id2, id3 -> navController.navigate( route = EvaluarSolicitudArtistico( id_solicitud = id1, id_usuario = id2, id_perfil = id3 ) ) })
        }
        composable<EvaluarSolicitudArtistico> {
            val evaluarSolicitudArtistico: EvaluarSolicitudArtistico = it.toRoute()
            PantallaEvaluarSolicitud(id_solicitud = evaluarSolicitudArtistico.id_solicitud, id_perfil = evaluarSolicitudArtistico.id_perfil, id_usuario = evaluarSolicitudArtistico.id_usuario, navegarInicio = { id, idPerfil -> navController.navigate(route = Inicio( id = id, id_perfil = idPerfil )) } )
        }
        composable<SolicitudesAprobadasEvaluadorArtistico> {
            val solicitudesAprobadasEvaluadorArtistico: SolicitudesAprobadasEvaluadorArtistico = it.toRoute()
            PantallaSolicitudesAprobadasEvaluadorArtistico(id = solicitudesAprobadasEvaluadorArtistico.id, id_perfil = solicitudesAprobadasEvaluadorArtistico.id_perfil )
        }

        // EVALUADOR ECONOMICO
        composable<SolicitudesRegistradasEvaluadorEconomico> {
            val solicitudesRegistradasEvaluadorEconomico: SolicitudesRegistradasEvaluadorEconomico = it.toRoute()
            PantallaSolicitudesRegistradasEvaluadorEconomico(id = solicitudesRegistradasEvaluadorEconomico.id, id_perfil = solicitudesRegistradasEvaluadorEconomico.id_perfil, navegarEvaluarSolicitudEconomico = { id, id_perfil, id_solicitud -> navController.navigate(route = EvaluacionEconomica( id = id, id_perfil = id_perfil, id_solicitud = id_solicitud )) } )
        }
        composable<EvaluacionEconomica> {
            val evaluacionEconomica: EvaluacionEconomica = it.toRoute()
            PantallaEvaluacionEconomica(id = evaluacionEconomica.id, id_perfil = evaluacionEconomica.id_perfil, id_solicitud = evaluacionEconomica.id_solicitud, navegarInicio = { id, idPerfil -> navController.navigate(route = Inicio( id = id, id_perfil = idPerfil )) } )
        }
        composable<DetalleAprobadoEconomico> {
            val detalleAprobadoEconomico: DetalleAprobadoEconomico = it.toRoute()
            PantallaDetalleSolicitudAprobadoEconomico(id_usuario = detalleAprobadoEconomico.id_usuario, id_perfil = detalleAprobadoEconomico.id_perfil, id_solicitud = detalleAprobadoEconomico.id_solicitud, navegarInicio = { id, idPerfil -> navController.navigate(route = Inicio( id = id, id_perfil = idPerfil )) } )
        }
        composable<SolicitudesAprobadasEvaluadorEconomico> {
            val solicitudesAprobadasEvaluadorEconomico: SolicitudesAprobadasEvaluadorEconomico = it.toRoute()
            PantallaListarObrasAprobadas(id = solicitudesAprobadasEvaluadorEconomico.id, id_perfil = solicitudesAprobadasEvaluadorEconomico.id_perfil , verDetalleSolicitud = {id_solicitud, id_usuario, id_perfil -> navController.navigate(route = DetalleAprobadoEconomico(id_usuario = id_usuario, id_perfil = id_perfil, id_solicitud = id_solicitud))})
        }

        // GERENTE
        composable<VerExpertos> {
            val verExpertos: VerExpertos = it.toRoute()
            PantallaGestionExpertos(id = verExpertos.id, id_perfil = verExpertos.id_perfil )
        }
        composable<VerTecnicas> {
            val verTecnicas: VerTecnicas = it.toRoute()
            PantallaGestionTecnicas(id = verTecnicas.id, id_perfil = verTecnicas.id_perfil )
        }
        composable<VerReportes> {
            val verReportes: VerReportes = it.toRoute()
            PantallaDashboardReportes(id = verReportes.id, id_perfil = verReportes.id_perfil )
        }
    }
}