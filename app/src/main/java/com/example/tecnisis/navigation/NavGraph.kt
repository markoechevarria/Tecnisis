package com.example.tecnisis.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tecnisis.ui.screens.anfitrion.PantallaBusquedaArtista
import com.example.tecnisis.ui.screens.anfitrion.PantallaConfirmarSolicitud
import com.example.tecnisis.ui.screens.anfitrion.PantallaListarExpertosDisponibles
import com.example.tecnisis.ui.screens.anfitrion.PantallaRegistrarArtista
import com.example.tecnisis.ui.screens.anfitrion.PantallaRegistrarObra
import com.example.tecnisis.ui.screens.anfitrion.PantallaSolicitudExitosa
import com.example.tecnisis.ui.screens.evaluadorArtistico.PantallaDetalleSolicitud
import com.example.tecnisis.ui.screens.evaluadorArtistico.PantallaEvaluarSolicitud
import com.example.tecnisis.ui.screens.evaluadorArtistico.PantallaSolicitudesRegistradas
import com.example.tecnisis.ui.screens.evaluadorEconomico.PantallaEvaluacionEconomica
import com.example.tecnisis.ui.screens.evaluadorEconomico.PantallaListarObrasAprobadas
import com.example.tecnisis.ui.screens.gerente.PantallaDashboardReportes
import com.example.tecnisis.ui.screens.gerente.PantallaGestionExpertos
import com.example.tecnisis.ui.screens.gerente.PantallaGestionTecnicas
import com.example.tecnisis.ui.screens.gerente.PantallaNuevaTecnica
import com.example.tecnisis.ui.screens.gerente.PantallaNuevoExperto
import com.example.tecnisis.ui.screens.gerente.PantallaReporte
import com.example.tecnisis.ui.screens.inicio.PantallaInicio
import com.example.tecnisis.ui.screens.login.LoginScreen
import com.example.tecnisis.ui.screens.login.SeleccionPerfilScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

@Composable
fun AppNavGraph(
    auth: FirebaseAuth,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Rutas.LOGIN
    ) {
        composable(Rutas.LOGIN) { LoginScreen( navController,auth) }
        composable(Rutas.SELECCION_PERFIL) { SeleccionPerfilScreen(navController) }
        composable(Rutas.INICIO) { PantallaInicio(navController) }
        composable(Rutas.BUSQUEDA_ARTISTA) { PantallaBusquedaArtista(navController) }
        composable(Rutas.CONFIRMAR_SOLICITUD) { PantallaConfirmarSolicitud(navController) }
        composable(Rutas.LISTAR_EXPERTOS_DISPONIBLES) { PantallaListarExpertosDisponibles(navController) }
        composable(Rutas.REGISTRAR_ARTISTA) { PantallaRegistrarArtista(navController) }
        composable(Rutas.REGISTRAR_OBRA) { PantallaRegistrarObra(navController) }
        composable(Rutas.SOLICITUD_EXITOSA) { PantallaSolicitudExitosa(navController) }
        composable(Rutas.DETALLE_SOLICITUD) { PantallaDetalleSolicitud(navController) }
        // composable(Rutas.EVALUAR_SOLICITUD) { PantallaEvaluarSolicitud(navController) }
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
