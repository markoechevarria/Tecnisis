package com.example.tecnisis.navigation

object Rutas {
    // Rutas de autenticación
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val INICIO = "inicio"

    // Rutas de artista
    const val MIS_OBRAS = "mis_obras"
    const val REGISTRAR_MI_OBRA = "registrar_mi_obra"
    const val INFORMACION_PERSONAL = "informacion_personal"
    const val SEGUIMIENTO_SOLICITUDES = "seguimiento_solicitudes"

    // Rutas de anfitrión
    const val BUSQUEDA_ARTISTA = "busqueda_artista"
    const val CONFIRMAR_SOLICITUD = "confirmar_solicitud"
    const val REGISTRAR_ARTISTA = "registrar_artista"
    const val REGISTRAR_OBRA = "registrar_obra"
    const val GESTION_SOLICITUDES = "gestion_solicitudes"

    // Rutas de evaluador artístico
    const val DETALLE_SOLICITUD = "detalle_solicitud"
    const val EVALUAR_SOLICITUD = "evaluar_solicitud"
    const val SOLICITUDES_REGISTRADAS = "solicitudes_registradas"

    // Rutas de evaluador económico
    const val EVALUACION_ECONOMICA = "evaluacion_economica"
    const val LISTA_OBRAS_APROBADAS = "lista_obras_aprobadas"

    // Rutas de gerente
    const val DASHBOARD_REPORTES = "dashboard_reportes"
    const val GESTION_EXPERTOS = "gestion_expertos"
    const val GESTION_TECNICAS = "geston_tecnicas"
    const val NUEVA_TECNICA = "nueva_tecnica"
    const val NUEVO_EXPERTO = "nuevo_experto"
    const val REPORTE = "reporte"

    // Ruta única reactiva de solicitudes
    const val SOLICITUDES = "solicitudes"
}

object UserTypes {
    const val ARTISTA = "ARTISTA"
    const val ANFITRION = "ANFITRION"
    const val EVALUADOR_ARTISTICO = "EVALUADOR_ARTISTICO"
    const val EVALUADOR_ECONOMICO = "EVALUADOR_ECONOMICO"
    const val GERENTE = "GERENTE"
    const val ADMIN = "ADMIN"
}