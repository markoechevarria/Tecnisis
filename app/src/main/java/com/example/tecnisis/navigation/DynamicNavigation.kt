package com.example.tecnisis.navigation

import com.example.tecnisis.ui.casosDeUso.inicio.MenuOption

object DynamicNavigation {
    
    fun getMenuOptions(userType: String?): List<MenuOption> {
        return when (userType?.uppercase()) {
            UserTypes.ARTISTA -> getArtistaMenuOptions()
            UserTypes.ANFITRION -> getAnfitrionMenuOptions()
            UserTypes.EVALUADOR_ARTISTICO -> getEvaluadorArtisticoMenuOptions()
            UserTypes.EVALUADOR_ECONOMICO -> getEvaluadorEconomicoMenuOptions()
            UserTypes.GERENTE -> getGerenteMenuOptions()
            UserTypes.ADMIN -> getAdminMenuOptions()
            else -> emptyList()
        }
    }
    
    fun getDisplayName(userType: String?): String {
        return when (userType?.uppercase()) {
            UserTypes.ARTISTA -> "Artista"
            UserTypes.ANFITRION -> "Anfitrión"
            UserTypes.EVALUADOR_ARTISTICO -> "Evaluador Artístico"
            UserTypes.EVALUADOR_ECONOMICO -> "Evaluador Económico"
            UserTypes.GERENTE -> "Gerente"
            UserTypes.ADMIN -> "Administrador"
            else -> "Usuario"
        }
    }
    
    private fun getArtistaMenuOptions(): List<MenuOption> = listOf(
        MenuOption(
            title = "Mis Obras",
            route = Rutas.MIS_OBRAS,
            description = "Ver el estado de tus obras registradas"
        ),
        MenuOption(
            title = "Registrar Nueva Obra",
            route = Rutas.REGISTRAR_MI_OBRA,
            description = "Registrar una nueva obra artística"
        )
    )
    
    private fun getAnfitrionMenuOptions(): List<MenuOption> = listOf(
        MenuOption(
            title = "Búsqueda de Artista",
            route = Rutas.BUSQUEDA_ARTISTA,
            description = "Buscar artistas por DNI"
        ),
        MenuOption(
            title = "Registrar Artista",
            route = Rutas.REGISTRAR_ARTISTA,
            description = "Registrar un nuevo artista"
        ),
        MenuOption(
            title = "Registrar Obra",
            route = Rutas.REGISTRAR_OBRA,
            description = "Registrar una nueva obra"
        ),
        MenuOption(
            title = "Gestión de Solicitudes",
            route = Rutas.GESTION_SOLICITUDES,
            description = "Ver y gestionar todas las solicitudes"
        )
    )
    
    private fun getEvaluadorArtisticoMenuOptions(): List<MenuOption> = listOf(
        MenuOption(
            title = "Solicitudes Registradas",
            route = Rutas.SOLICITUDES_REGISTRADAS,
            description = "Ver solicitudes pendientes de evaluación"
        ),
        MenuOption(
            title = "Evaluar Solicitud",
            route = Rutas.EVALUAR_SOLICITUD,
            description = "Evaluar solicitudes artísticas"
        )
    )
    
    private fun getEvaluadorEconomicoMenuOptions(): List<MenuOption> = listOf(
        MenuOption(
            title = "Obras Aprobadas",
            route = Rutas.LISTA_OBRAS_APROBADAS,
            description = "Ver obras aprobadas para evaluación económica"
        ),
        MenuOption(
            title = "Evaluación Económica",
            route = Rutas.EVALUACION_ECONOMICA,
            description = "Realizar evaluación económica de obras"
        )
    )
    
    private fun getGerenteMenuOptions(): List<MenuOption> = listOf(
        MenuOption(
            title = "Dashboard de Reportes",
            route = Rutas.DASHBOARD_REPORTES,
            description = "Ver reportes y estadísticas del sistema"
        ),
        MenuOption(
            title = "Gestión de Expertos",
            route = Rutas.GESTION_EXPERTOS,
            description = "Gestionar expertos del sistema"
        ),
        MenuOption(
            title = "Gestión de Técnicas",
            route = Rutas.GESTION_TECNICAS,
            description = "Gestionar técnicas artísticas"
        ),
        MenuOption(
            title = "Nuevo Experto",
            route = Rutas.NUEVO_EXPERTO,
            description = "Registrar un nuevo experto"
        ),
        MenuOption(
            title = "Nueva Técnica",
            route = Rutas.NUEVA_TECNICA,
            description = "Crear una nueva técnica artística"
        ),
        MenuOption(
            title = "Reportes",
            route = Rutas.REPORTE,
            description = "Generar reportes específicos"
        )
    )
    
    private fun getAdminMenuOptions(): List<MenuOption> = listOf(
        MenuOption(
            title = "Búsqueda de Artista",
            route = Rutas.BUSQUEDA_ARTISTA,
            description = "Buscar artistas en el sistema"
        ),
        MenuOption(
            title = "Solicitudes Registradas",
            route = Rutas.SOLICITUDES_REGISTRADAS,
            description = "Ver todas las solicitudes"
        ),
        MenuOption(
            title = "Obras Aprobadas",
            route = Rutas.LISTA_OBRAS_APROBADAS,
            description = "Ver obras aprobadas"
        ),
        MenuOption(
            title = "Gestión de Técnicas",
            route = Rutas.GESTION_TECNICAS,
            description = "Gestionar técnicas artísticas"
        ),
        MenuOption(
            title = "Gestión de Expertos",
            route = Rutas.GESTION_EXPERTOS,
            description = "Gestionar expertos del sistema"
        ),
        MenuOption(
            title = "Dashboard de Reportes",
            route = Rutas.DASHBOARD_REPORTES,
            description = "Ver reportes del sistema"
        )
    )
} 