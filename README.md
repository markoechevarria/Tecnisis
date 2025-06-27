# Tecnisis DSM

Aplicación móvil y backend para la gestión de solicitudes y evaluaciones de obras de arte, artistas y expertos, diseñada para diferentes roles (anfitrión, evaluador artístico, evaluador económico, gerente). El frontend está desarrollado en Kotlin usando Jetpack Compose, y el backend en Java con Spring Boot.

Este proyecto demuestra el uso de componentes modernos de arquitectura Android (MVVM, ViewModel, StateFlow, Navigation Compose) y una API REST robusta con Spring Boot.

---

## Pre-requisitos

### Para la app móvil (Android)
- Experiencia con sintaxis de Kotlin.
- Conocimientos básicos de Jetpack Compose.
- Saber crear y ejecutar proyectos en Android Studio.
- Conocimientos sobre ViewModel, StateFlow y funciones composables.
- Conocimientos básicos de navegación en Compose.

### Para el backend (Java)
- Experiencia con Java y Spring Boot.
- Conocimientos de APIs REST.
- Saber ejecutar proyectos con Gradle.

---

## Estructura del Proyecto

- **app/**: Aplicación Android (Kotlin + Jetpack Compose)
    - Pantallas para cada rol: anfitrión, evaluador artístico, evaluador económico, gerente.
    - Gestión de estado con ViewModel y UiState.
    - Navegación dinámica con Navigation Compose.
    - Temas personalizados con MaterialTheme.
- **backend/**: API REST (Java + Spring Boot)
    - Controladores para autenticación, registro, gestión de artistas, obras y evaluaciones.
    - Entidades y repositorios JPA para persistencia de datos.
    - DTOs y mapeadores para transferencia de datos.

---

## Cómo empezar

### App móvil (Android)
1. Instala Android Studio si aún no lo tienes.
2. Descarga o clona este repositorio.
3. Importa el proyecto en Android Studio.
4. Construye y ejecuta la app en un emulador o dispositivo físico.

### Backend (Spring Boot)
1. Asegúrate de tener Java 17+ y Gradle instalados.
2. Navega a la carpeta `backend/`.
3. Ejecuta el backend con el comando:
   ```
   ./gradlew bootRun
   ```
4. El backend estará disponible en `http://localhost:8080`.

---

## Funcionalidades principales

- Registro y autenticación de usuarios.
- Gestión de artistas y obras de arte.
- Evaluación artística y económica de solicitudes.
- Panel de control y reportes para gerentes.
- Gestión de expertos y técnicas artísticas.
- Navegación fluida y moderna con Jetpack Compose.

---

## Herramientas y tecnologías utilizadas

- **Frontend:** Kotlin, Jetpack Compose, ViewModel, StateFlow, Navigation Compose, MaterialTheme.
- **Backend:** Java, Spring Boot, Spring Data JPA, Gradle.

---

## Créditos

Desarrollado por el equipo de Tecnisis DSM.
