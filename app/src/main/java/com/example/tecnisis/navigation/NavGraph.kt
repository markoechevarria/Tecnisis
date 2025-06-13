package com.example.tecnisis.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tecnisis.ui.casosDeUso.anfitrion.busquedaArtista.PantallaBusquedaArtista
import com.example.tecnisis.ui.casosDeUso.anfitrion.confirmarSolicitud.PantallaConfirmarSolicitud
import com.example.tecnisis.ui.casosDeUso.anfitrion.listarExpertosDisponibles.PantallaListarExpertosDisponibles
import com.example.tecnisis.ui.casosDeUso.anfitrion.registrarArtista.PantallaRegistrarArtista
import com.example.tecnisis.ui.casosDeUso.anfitrion.registrarObra.PantallaRegistrarObra
import com.example.tecnisis.ui.casosDeUso.anfitrion.solicitudExitosa.PantallaSolicitudExitosa
import com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.detalleSolicitud.PantallaDetalleSolicitud
import com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.evaluarSolicitud.PantallaEvaluarSolicitud
import com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesRegistradas.PantallaSolicitudesRegistradas
import com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.evaluacionEconomica.PantallaEvaluacionEconomica
import com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.listaObrasAprobadas.PantallaListarObrasAprobadas
import com.example.tecnisis.ui.casosDeUso.gerente.dashboardReportes.PantallaDashboardReportes
import com.example.tecnisis.ui.casosDeUso.gerente.gestionExpertos.PantallaGestionExpertos
import com.example.tecnisis.ui.casosDeUso.gerente.gestionTecnicas.PantallaGestionTecnicas
import com.example.tecnisis.ui.casosDeUso.gerente.nuevaTecnica.PantallaNuevaTecnica
import com.example.tecnisis.ui.casosDeUso.gerente.nuevoExperto.PantallaNuevoExperto
import com.example.tecnisis.ui.casosDeUso.gerente.reporte.PantallaReporte
import com.example.tecnisis.ui.casosDeUso.inicio.PantallaInicio
import com.example.tecnisis.ui.casosDeUso.login.login.LoginScreen
import com.example.tecnisis.ui.casosDeUso.login.seleccionarPerfil.SeleccionPerfilScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Rutas.LOGIN
    ) {
        composable(Rutas.LOGIN) { LoginScreen( navController) }
        composable(Rutas.SELECCION_PERFIL) { SeleccionPerfilScreen(navController) }
        composable(Rutas.INICIO) { PantallaInicio(navController) }
        composable(Rutas.BUSQUEDA_ARTISTA) { PantallaBusquedaArtista(navController) }
        composable(Rutas.CONFIRMAR_SOLICITUD) { PantallaConfirmarSolicitud(navController) }
        composable(Rutas.LISTAR_EXPERTOS_DISPONIBLES) { PantallaListarExpertosDisponibles(navController) }
        composable(Rutas.REGISTRAR_ARTISTA) { PantallaRegistrarArtista(navController) }
        composable(Rutas.REGISTRAR_OBRA) { PantallaRegistrarObra(navController) }
        composable(Rutas.SOLICITUD_EXITOSA) { PantallaSolicitudExitosa(navController) }
        composable(Rutas.DETALLE_SOLICITUD) { PantallaDetalleSolicitud(navController) }
        composable(Rutas.EVALUAR_SOLICITUD) { PantallaEvaluarSolicitud(navController) }
        composable(Rutas.SOLICITUDES_REGISTRADAS) { PantallaSolicitudesRegistradas(navController) }
        composable(Rutas.EVALUACION_ECONOMICA) { PantallaEvaluacionEconomica(navController) }
        composable(Rutas.LISTA_OBRAS_APROBADAS) { PantallaListarObrasAprobadas(navController) }
        composable(Rutas.DASHBOARD_REPORTES) { PantallaDashboardReportes(navController) }
        composable(Rutas.GESTION_EXPERTOS) { PantallaGestionExpertos(navController) }
        composable(Rutas.GESTION_TECNICAS) { PantallaGestionTecnicas(navController) }
        composable(Rutas.NUEVA_TECNICA) { PantallaNuevaTecnica(navController) }
        composable(Rutas.NUEVO_EXPERTO) { PantallaNuevoExperto(navController) }
        composable(Rutas.REPORTE) { PantallaReporte(navController) }
    }
}
