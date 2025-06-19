package com.example.tecnisis.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tecnisis.ui.casosDeUso.anfitrion.confirmarSolicitud.PantallaConfirmarSolicitud
import com.example.tecnisis.ui.casosDeUso.anfitrion.listarExpertosDisponibles.PantallaListarExpertosDisponibles
import com.example.tecnisis.ui.casosDeUso.anfitrion.registrarObra.PantallaRegistrarObra
import com.example.tecnisis.ui.casosDeUso.anfitrion.solicitudExitosa.PantallaSolicitudExitosa
import com.example.tecnisis.ui.casosDeUso.gerente.nuevaTecnica.PantallaNuevaTecnica
import com.example.tecnisis.ui.casosDeUso.gerente.nuevoExperto.PantallaNuevoExperto
import com.example.tecnisis.ui.casosDeUso.gerente.reporte.PantallaReporte

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Rutas.LOGIN
    ) {
        composable(Rutas.CONFIRMAR_SOLICITUD) { PantallaConfirmarSolicitud(navController) }
        composable(Rutas.LISTAR_EXPERTOS_DISPONIBLES) { PantallaListarExpertosDisponibles(navController) }
        composable(Rutas.REGISTRAR_OBRA) { PantallaRegistrarObra(navController) }
        composable(Rutas.SOLICITUD_EXITOSA) { PantallaSolicitudExitosa(navController) }
        composable(Rutas.NUEVA_TECNICA) { PantallaNuevaTecnica(navController) }
        composable(Rutas.NUEVO_EXPERTO) { PantallaNuevoExperto(navController) }
        composable(Rutas.REPORTE) { PantallaReporte(navController) }
    }
}