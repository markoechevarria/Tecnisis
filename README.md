# Tecnisis

Aplicación móvil y backend para la gestión de solicitudes y evaluaciones de obras de arte, artistas y expertos, diseñada para diferentes roles (anfitrión, evaluador artístico, evaluador económico, gerente). El frontend está desarrollado en Kotlin usando Jetpack Compose, y el backend en Python con FastAPI.

Este proyecto demuestra el uso de componentes modernos de arquitectura Android (MVVM, ViewModel, StateFlow, Navigation Compose, Inyección de Dependencias con Hilt, Crashlytics y almacenamiento de imágenes con Firebase, Repositories y Retrofit para llamadas a la API) y una API REST con FastAPI.

---

## Demostracion

<p align="center">
  <img src="preview_1.gif" alt="Demo 1" width="300" style="margin-right: 20px;">
  <img src="preview_2.gif" alt="Demo 2" width="300">
</p>

---

## APK de la aplicacion

<p align="center">
  <a href="https://github.com/markoechevarria/Tecnisis/blob/main/app/release/app-release.apk" download>
    <img src="https://img.shields.io/badge/Descargar%20APK-Última%20Versión-green?style=for-the-badge&logo=android" alt="Descargar APK">
  </a>
</p>

---

## Estructura del Proyecto

- **app/**: Aplicación Android (Kotlin + Jetpack Compose)
    - Pantallas para cada rol: anfitrión, evaluador artístico, evaluador económico, gerente.
    - Gestión de estado con ViewModel y UiState.
    - Navegación dinámica con Navigation Compose.
    - Temas personalizados con MaterialTheme.
    - Inyección de dependencias con Hilt.
    - Integración con Firebase para Crashlytics y almacenamiento de imágenes.
    - Uso de Patrón Repositorio y Retrofit para la capa de datos.
- **backend/**: FastAPI
    - Controladores para autenticación, registro, gestión de artistas, obras y evaluaciones.
    - Endpoints para autenticación, registro, gestión de artistas, obras y evaluaciones.
    - Modelos de datos con Pydantic.
    - Interacción con PostgreSQL para persistencia de datos.

---

## Enlace al repositorio de backend

[Tecnisis Backend FastAPI](https://github.com/markoechevarria/Tecnisis-Backend-FastAPI)

---

## Funcionalidades principales

- Registro y autenticación de usuarios.
- Gestión de artistas y obras de arte.
-Evaluación artística y económica de solicitudes.
- Panel de control y reportes para gerentes.
- Gestión de expertos y técnicas artísticas.
- Navegación fluida y moderna con Jetpack Compose.
- Reporte de errores con Crashlytics (Firebase).
- Almacenamiento de imágenes en la nube (Firebase Storage).
---

## Herramientas y tecnologías utilizadas

- **Frontend:** Kotlin, Jetpack Compose, ViewModel, StateFlow, Navigation Compose, MaterialTheme, Hilt, Firebase (Crashlytics, Storage), Retrofit.
- **Backend:** Python, FastAPI, Pydantic, PostgreSQL, SQLAlchemy.

---
