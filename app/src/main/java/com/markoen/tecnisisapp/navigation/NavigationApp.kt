package com.markoen.tecnisisapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.markoen.tecnisisapp.ui.views.anfitrion.busquedaArtista.PantallaBusquedaArtista
import com.markoen.tecnisisapp.ui.views.anfitrion.confirmarSolicitud.PantallaConfirmarSolicitud
import com.markoen.tecnisisapp.ui.views.anfitrion.listarExpertosDisponibles.PantallaListarExpertosDisponibles
import com.markoen.tecnisisapp.ui.views.anfitrion.registrarArtista.PantallaRegistrarArtista
import com.markoen.tecnisisapp.ui.views.anfitrion.registrarObra.PantallaRegistrarObra
import com.markoen.tecnisisapp.ui.views.anfitrion.solicitudExitosa.PantallaSolicitudExitosa
import com.markoen.tecnisisapp.ui.views.anfitrion.solicitudesRegistradasAnfitrion.PantallaSolicitudesRegistradasAnfitrion
import com.markoen.tecnisisapp.ui.views.evaluadorArtistico.detalleSolicitud.PantallaDetalleSolicitudEvaluadorArtistico
import com.markoen.tecnisisapp.ui.views.evaluadorArtistico.evaluarSolicitud.PantallaEvaluarSolicitud
import com.markoen.tecnisisapp.ui.views.evaluadorArtistico.solicitudesAprobadasEvaluadorArtistico.PantallaSolicitudesAprobadasEvaluadorArtistico
import com.markoen.tecnisisapp.ui.views.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico.PantallaSolicitudesRegistradasEvaluadorArtistico
import com.markoen.tecnisisapp.ui.views.evaluadorEconomico.detalleSolicitudAprobadaEconomico.PantallaDetalleSolicitudAprobadoEconomico
import com.markoen.tecnisisapp.ui.views.evaluadorEconomico.evaluacionEconomica.PantallaEvaluacionEconomica
import com.markoen.tecnisisapp.ui.views.evaluadorEconomico.listaObrasAprobadas.PantallaListarObrasAprobadas
import com.markoen.tecnisisapp.ui.views.evaluadorEconomico.solicitudesRegistradasEvaluadorEconomico.PantallaSolicitudesRegistradasEvaluadorEconomico
import com.markoen.tecnisisapp.ui.views.gerente.editarExperto.PantallaEditarExperto
import com.markoen.tecnisisapp.ui.views.gerente.editarTecnica.PantallaEditarTecnica
import com.markoen.tecnisisapp.ui.views.gerente.listaOpcionesReportes.PantallaListaOpcionesReportes
import com.markoen.tecnisisapp.ui.views.gerente.gestionExpertos.PantallaGestionExpertos
import com.markoen.tecnisisapp.ui.views.gerente.gestionTecnicas.PantallaGestionTecnicas
import com.markoen.tecnisisapp.ui.views.gerente.nuevaTecnica.PantallaNuevaTecnica
import com.markoen.tecnisisapp.ui.views.gerente.nuevoExperto.PantallaNuevoExperto
import com.markoen.tecnisisapp.ui.views.gerente.reporte.PantallaReporte
import com.markoen.tecnisisapp.ui.views.inicio.PantallaInicio
import com.markoen.tecnisisapp.ui.views.login.LoginScreen

@Composable
fun NavigationApp () {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Login) {

        // LOGIN

        composable<Login> {
            LoginScreen (navigateToPrincipal = { id1, id_perfil -> navController.navigate(route = Inicio(id = id1, id_perfil = id_perfil ) ) } )
        }

        // INICIO

        composable<Inicio> { val inicio : Inicio = it.toRoute()
            PantallaInicio( id = inicio.id, id_perfil = inicio.id_perfil , volverInicio = {
                navController.navigate(route = Login)
            }, navegarOpcion = { id, id_perfil ->
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
                }
            })
        }

        // ANFITRION

        composable<BusquedaArtista> { val busquedaArtista: BusquedaArtista = it.toRoute()
            PantallaBusquedaArtista(id = busquedaArtista.id, id_perfil = busquedaArtista.id_perfil, navegarRegistarArtista = { id, id_perfil -> navController.navigate(route = RegistrarArtista(id = id, id_perfil = id_perfil)) }, navegarRegistrarObra = { id, id_perfil, id_artista -> navController.navigate(route = RegistrarObra(id, id_perfil, id_artista)) } )
        }
        composable<RegistrarArtista> { val registrarArtista: RegistrarArtista = it.toRoute()
            PantallaRegistrarArtista(id = registrarArtista.id, id_perfil = registrarArtista.id_perfil )
        }
        composable<RegistrarObra> { val registrarObra: RegistrarObra = it.toRoute()
            PantallaRegistrarObra(id = registrarObra.id, id_perfil = registrarObra.id_perfil, id_artista = registrarObra.id_artista , navegarElegirExperto = { id, id_perfil, id_artista, id_obra -> navController.navigate( route = ListarExpertos(id = id, id_perfil = id_perfil, id_artista = id_artista, id_obra = id_obra )) } )
        }
        composable<ListarExpertos> { val listarExpertos: ListarExpertos = it.toRoute()
            PantallaListarExpertosDisponibles( id=listarExpertos.id, id_perfil = listarExpertos.id_perfil, id_artista = listarExpertos.id_artista ,id_obra = listarExpertos.id_obra, confirmarSolicitud =  {id, id_perfil, id_artista, id_obra, id_evaluador_economico -> navController.navigate(route= ConfirmarSolicitud(id, id_perfil, id_artista, id_obra, id_evaluador_economico))} )
        }
        composable<ConfirmarSolicitud> { val confirmarSolicitud: ConfirmarSolicitud = it.toRoute()
            PantallaConfirmarSolicitud( id=confirmarSolicitud.id, id_perfil = confirmarSolicitud.id_perfil, id_artista = confirmarSolicitud.id_artista, id_obra = confirmarSolicitud.id_obra, id_evaluador_artistico = confirmarSolicitud.id_evaluador_economico, solicitudExitosa =  {id, id_perfil -> navController.navigate(route= SolicitudExitosa(id, id_perfil))} )
        }
        composable<SolicitudExitosa> { val solicitudExitosa: SolicitudExitosa = it.toRoute()
            PantallaSolicitudExitosa( id=solicitudExitosa.id, id_perfil =solicitudExitosa.id_perfil, navegarInicio = {id, id_perfil -> navController.navigate(route = Inicio(id, id_perfil))} )
        }
        composable<SolicitudesRegistradasAnfitrion> { val solicitudesRegistradasAnfitrion: SolicitudesRegistradasAnfitrion = it.toRoute()
            PantallaSolicitudesRegistradasAnfitrion(id = solicitudesRegistradasAnfitrion.id, id_perfil = solicitudesRegistradasAnfitrion.id_perfil )
        }

        // EVALUADOR ARTISTICO

        composable<SolicitudesRegistradasEvaluadorArtistico> { val solicitudesRegistradasEvaluadorArtistico: SolicitudesRegistradasEvaluadorArtistico = it.toRoute()
            PantallaSolicitudesRegistradasEvaluadorArtistico( verDetalleSolicitud = { id1, id2, id3 -> navController.navigate(route = DetalleSolicitudesEvaluadorArtistico(id_solicitud=id1, id_usuario=id2, id_perfil=id3) ) } , id = solicitudesRegistradasEvaluadorArtistico.id, id_perfil = solicitudesRegistradasEvaluadorArtistico.id_perfil )
        }
        composable<DetalleSolicitudesEvaluadorArtistico> { val detalleSolicitudesEvaluadorArtistico: DetalleSolicitudesEvaluadorArtistico = it.toRoute()
            PantallaDetalleSolicitudEvaluadorArtistico( id_solicitud = detalleSolicitudesEvaluadorArtistico.id_solicitud, id_usuario = detalleSolicitudesEvaluadorArtistico.id_usuario, id_perfil = detalleSolicitudesEvaluadorArtistico.id_perfil, navegarEvaluacion = { id1, id2, id3 -> navController.navigate( route = EvaluarSolicitudArtistico( id_solicitud = id1, id_usuario = id2, id_perfil = id3 ) ) })
        }
        composable<EvaluarSolicitudArtistico> { val evaluarSolicitudArtistico: EvaluarSolicitudArtistico = it.toRoute()
            PantallaEvaluarSolicitud(id_solicitud = evaluarSolicitudArtistico.id_solicitud, id_perfil = evaluarSolicitudArtistico.id_perfil, id_usuario = evaluarSolicitudArtistico.id_usuario, navegarInicio = { id, idPerfil -> navController.navigate(route = Inicio( id = id, id_perfil = idPerfil )) } )
        }
        composable<SolicitudesAprobadasEvaluadorArtistico> { val solicitudesAprobadasEvaluadorArtistico: SolicitudesAprobadasEvaluadorArtistico = it.toRoute()
            PantallaSolicitudesAprobadasEvaluadorArtistico(id = solicitudesAprobadasEvaluadorArtistico.id, id_perfil = solicitudesAprobadasEvaluadorArtistico.id_perfil )
        }

        // EVALUADOR ECONOMICO

        composable<SolicitudesRegistradasEvaluadorEconomico> { val solicitudesRegistradasEvaluadorEconomico: SolicitudesRegistradasEvaluadorEconomico = it.toRoute()
            PantallaSolicitudesRegistradasEvaluadorEconomico(id = solicitudesRegistradasEvaluadorEconomico.id, id_perfil = solicitudesRegistradasEvaluadorEconomico.id_perfil, navegarEvaluarSolicitudEconomico = { id, id_perfil, id_solicitud -> navController.navigate(route = EvaluacionEconomica( id = id, id_perfil = id_perfil, id_solicitud = id_solicitud )) } )
        }
        composable<EvaluacionEconomica> { val evaluacionEconomica: EvaluacionEconomica = it.toRoute()
            PantallaEvaluacionEconomica(id = evaluacionEconomica.id, id_perfil = evaluacionEconomica.id_perfil, id_solicitud = evaluacionEconomica.id_solicitud, navegarInicio = { id, idPerfil -> navController.navigate(route = Inicio( id = id, id_perfil = idPerfil )) } )
        }
        composable<DetalleAprobadoEconomico> { val detalleAprobadoEconomico: DetalleAprobadoEconomico = it.toRoute()
            PantallaDetalleSolicitudAprobadoEconomico(id_usuario = detalleAprobadoEconomico.id_usuario, id_perfil = detalleAprobadoEconomico.id_perfil, id_solicitud = detalleAprobadoEconomico.id_solicitud, navegarInicio = { id, idPerfil -> navController.navigate(route = Inicio( id = id, id_perfil = idPerfil )) } )
        }
        composable<SolicitudesAprobadasEvaluadorEconomico> { val solicitudesAprobadasEvaluadorEconomico: SolicitudesAprobadasEvaluadorEconomico = it.toRoute()
            PantallaListarObrasAprobadas(id = solicitudesAprobadasEvaluadorEconomico.id, id_perfil = solicitudesAprobadasEvaluadorEconomico.id_perfil , verDetalleSolicitud = {id_solicitud, id_usuario, id_perfil -> navController.navigate(route = DetalleAprobadoEconomico(id_usuario = id_usuario, id_perfil = id_perfil, id_solicitud = id_solicitud))})
        }

        // GERENTE

        composable<VerExpertos> { val verExpertos: VerExpertos = it.toRoute()
            PantallaGestionExpertos(id = verExpertos.id, id_perfil = verExpertos.id_perfil, registrarNuevoExperto = { id, id_perfil -> navController.navigate(route = NuevoExperto(id, id_perfil)) }, actualizarExperto = {id, id_perfil, id_experto -> navController.navigate(route = EditarExperto(id, id_perfil, id_experto))} )
        }
        composable<VerTecnicas> { val verTecnicas: VerTecnicas = it.toRoute()
            PantallaGestionTecnicas(id = verTecnicas.id, id_perfil = verTecnicas.id_perfil, registrarNuevaTecnica = { id, id_perfil -> navController.navigate(route = NuevaTecnica(id, id_perfil)) }, actualizarTecnica = {id, id_perfil, id_tecnica -> navController.navigate(route = EditarTecnica(id, id_perfil, id_tecnica))} )
        }
        composable<VerReportes> { val verReportes: VerReportes = it.toRoute()
            PantallaListaOpcionesReportes(id = verReportes.id, id_perfil = verReportes.id_perfil )
        }
        composable<NuevoExperto> { val nuevoExperto: NuevoExperto = it.toRoute()
            PantallaNuevoExperto(id = nuevoExperto.id, id_perfil = nuevoExperto.id_perfil )
        }
        composable<NuevaTecnica> { val nuevaTecnica: NuevaTecnica = it.toRoute()
            PantallaNuevaTecnica(id = nuevaTecnica.id, id_perfil = nuevaTecnica.id_perfil )
        }
        composable<VerReporte> { val verReporte: VerReporte = it.toRoute()
            PantallaReporte(id = verReporte.id, id_perfil = verReporte.id_perfil )
        }
        composable<EditarExperto> { val editarExperto: EditarExperto = it.toRoute()
            PantallaEditarExperto(id = editarExperto.id, id_perfil = editarExperto.id_perfil, id_experto = editarExperto.id_experto )
        }
        composable<EditarTecnica> { val editarTecnica: EditarTecnica = it.toRoute()
            PantallaEditarTecnica(id = editarTecnica.id, id_perfil = editarTecnica.id_perfil, id_tecnica = editarTecnica.id_tecnica )
        }
    }
}