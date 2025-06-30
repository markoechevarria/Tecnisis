package com.markoen.tecnisisapp.ui.test

import com.markoen.tecnisisapp.ui.views.evaluadorArtistico.solicitudesRegistradas.SolicitudesRegistradasViewModel
import com.markoen.tecnisisapp.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SolicitudesRegistradasViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SolicitudesRegistradasViewModel

    @Before
    fun setUp() {
        viewModel = SolicitudesRegistradasViewModel()
    }


    @Test
    fun `init carga solicitudes correctamente`() = runTest {
        // Given - ViewModel se inicializa en setUp()

        // When - esperamos que termine la inicialización
        advanceUntilIdle()

        // Then - verificamos que los datos se cargaron
        val uiState = viewModel.uiState.value
        assertFalse(uiState.isLoading)
        assertEquals(2, uiState.solicitudes.size)
        assertEquals(2, uiState.solicitudesFiltradas.size)
        assertEquals("TODAS", uiState.filtroEstado)
        assertNull(uiState.error)

        // Verificamos las solicitudes específicas
        val solicitud1 = uiState.solicitudes[0]
        assertEquals("S001", solicitud1.id)
        assertEquals("Juan Pérez", solicitud1.artistaNombre)
        assertEquals("Paisaje Nocturno", solicitud1.tituloObra)
        assertEquals("2025-01-15", solicitud1.fechaSolicitud)
        assertEquals("PENDIENTE_ARTISTICA", solicitud1.estado)

        val solicitud2 = uiState.solicitudes[1]
        assertEquals("S002", solicitud2.id)
        assertEquals("María García", solicitud2.artistaNombre)
        assertEquals("Retrato Abstracto", solicitud2.tituloObra)
        assertEquals("2025-01-14", solicitud2.fechaSolicitud)
        assertEquals("EN_EVALUACION_ARTISTICA", solicitud2.estado)
    }

    @Test
    fun `filtrarPorEstado con TODAS muestra todas las solicitudes`() = runTest {
        // Given - ViewModel inicializado
        advanceUntilIdle()

        // When - filtramos por "TODAS"
        viewModel.filtrarPorEstado("TODAS")

        // Then - verificamos que se muestran todas las solicitudes
        val uiState = viewModel.uiState.value
        assertEquals("TODAS", uiState.filtroEstado)
        assertEquals(2, uiState.solicitudesFiltradas.size)
        assertEquals(uiState.solicitudes, uiState.solicitudesFiltradas)
    }

    @Test
    fun `filtrarPorEstado con PENDIENTE_ARTISTICA filtra correctamente`() = runTest {
        // Given - ViewModel inicializado
        advanceUntilIdle()

        // When - filtramos por "PENDIENTE_ARTISTICA"
        viewModel.filtrarPorEstado("PENDIENTE_ARTISTICA")

        // Then - verificamos que solo se muestra la solicitud pendiente
        val uiState = viewModel.uiState.value
        assertEquals("PENDIENTE_ARTISTICA", uiState.filtroEstado)
        assertEquals(1, uiState.solicitudesFiltradas.size)
        assertEquals("S001", uiState.solicitudesFiltradas[0].id)
        assertEquals("PENDIENTE_ARTISTICA", uiState.solicitudesFiltradas[0].estado)
    }

    @Test
    fun `filtrarPorEstado con EN_EVALUACION_ARTISTICA filtra correctamente`() = runTest {
        // Given - ViewModel inicializado
        advanceUntilIdle()

        // When - filtramos por "EN_EVALUACION_ARTISTICA"
        viewModel.filtrarPorEstado("EN_EVALUACION_ARTISTICA")

        // Then - verificamos que solo se muestra la solicitud en evaluación
        val uiState = viewModel.uiState.value
        assertEquals("EN_EVALUACION_ARTISTICA", uiState.filtroEstado)
        assertEquals(1, uiState.solicitudesFiltradas.size)
        assertEquals("S002", uiState.solicitudesFiltradas[0].id)
        assertEquals("EN_EVALUACION_ARTISTICA", uiState.solicitudesFiltradas[0].estado)
    }

    @Test
    fun `filtrarPorEstado con estado inexistente devuelve lista vacia`() = runTest {
        // Given - ViewModel inicializado
        advanceUntilIdle()

        // When - filtramos por un estado que no existe
        viewModel.filtrarPorEstado("ESTADO_INEXISTENTE")

        // Then - verificamos que no hay solicitudes filtradas
        val uiState = viewModel.uiState.value
        assertEquals("ESTADO_INEXISTENTE", uiState.filtroEstado)
        assertTrue(uiState.solicitudesFiltradas.isEmpty())
        // Las solicitudes originales no deben cambiar
        assertEquals(2, uiState.solicitudes.size)
    }

    @Test
    fun `clearError limpia el error del estado`() = runTest {
        // Given - ViewModel inicializado
        advanceUntilIdle()

        // When - limpiamos error
        viewModel.clearError()

        // Then - verificamos que no hay error
        assertNull(viewModel.uiState.value.error)
    }

    @Test
    fun `filtros multiples mantienen las solicitudes originales intactas`() = runTest {
        // Given - ViewModel inicializado
        advanceUntilIdle()
        val solicitudesOriginales = viewModel.uiState.value.solicitudes

        // When - aplicamos múltiples filtros
        viewModel.filtrarPorEstado("PENDIENTE_ARTISTICA")
        val estadoDespuesPrimero = viewModel.uiState.value

        viewModel.filtrarPorEstado("EN_EVALUACION_ARTISTICA")
        val estadoDespuesSegundo = viewModel.uiState.value

        viewModel.filtrarPorEstado("TODAS")
        val estadoFinal = viewModel.uiState.value

        // Then - verificamos que las solicitudes originales no cambiaron
        assertEquals(solicitudesOriginales, estadoDespuesPrimero.solicitudes)
        assertEquals(solicitudesOriginales, estadoDespuesSegundo.solicitudes)
        assertEquals(solicitudesOriginales, estadoFinal.solicitudes)

        // Y que los filtros funcionaron correctamente
        assertEquals(1, estadoDespuesPrimero.solicitudesFiltradas.size)
        assertEquals(1, estadoDespuesSegundo.solicitudesFiltradas.size)
        assertEquals(2, estadoFinal.solicitudesFiltradas.size)
    }



    @Test
    fun `solicitudesFiltradas se inicializa igual a solicitudes`() = runTest {
        // Given - ViewModel inicializado
        advanceUntilIdle()

        // When - obtenemos estado después de la carga
        val uiState = viewModel.uiState.value

        // Then - verificamos que las listas son iguales inicialmente
        assertEquals(uiState.solicitudes, uiState.solicitudesFiltradas)
        assertEquals("TODAS", uiState.filtroEstado)
    }

    @Test
    fun `filtrarPorEstado no afecta otros campos del estado`() = runTest {
        // Given - ViewModel inicializado
        advanceUntilIdle()
        val estadoInicial = viewModel.uiState.value

        // When - aplicamos filtro
        viewModel.filtrarPorEstado("PENDIENTE_ARTISTICA")

        // Then - verificamos que otros campos no cambiaron
        val estadoFinal = viewModel.uiState.value
        assertEquals(estadoInicial.solicitudes, estadoFinal.solicitudes)
        assertEquals(estadoInicial.isLoading, estadoFinal.isLoading)
        assertEquals(estadoInicial.error, estadoFinal.error)
        // Solo deben cambiar filtroEstado y solicitudesFiltradas
        assertEquals("PENDIENTE_ARTISTICA", estadoFinal.filtroEstado)
        assertNotEquals(estadoInicial.solicitudesFiltradas, estadoFinal.solicitudesFiltradas)
    }
}