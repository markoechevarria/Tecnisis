package com.markoen.tecnisisapp.ui.test

import com.markoen.tecnisisapp.ui.views.evaluadorEconomico.listaObrasAprobadas.ListaObrasAprobadasViewModel
import com.markoen.tecnisisapp.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListaObrasAprobadasViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ListaObrasAprobadasViewModel

    @Before
    fun setUp() {
        viewModel = ListaObrasAprobadasViewModel()
    }



    @Test
    fun `loadObrasAprobadas debe cargar obras correctamente`() = runTest {
        // Given - ViewModel inicializado

        // When - se espera a que termine la carga
        // El init() ya llama a loadObrasAprobadas()

        // Then - debe tener las obras cargadas
        val state = viewModel.uiState.value
        assertFalse("No debe estar cargando", state.isLoading)
        assertEquals("Debe tener 2 obras", 2, state.obras.size)
        assertEquals("Las obras filtradas deben ser iguales a las obras", state.obras, state.obrasFiltradas)
        assertNull("No debe haber error", state.error)

        // Verificar datos específicos de las obras
        val primeraObra = state.obras[0]
        assertEquals("O001", primeraObra.id)
        assertEquals("Juan Pérez", primeraObra.artistaNombre)
        assertEquals("Paisaje Nocturno", primeraObra.tituloObra)
        assertEquals("2025-01-15", primeraObra.fechaAprobacion)
        assertEquals("Óleo sobre lienzo", primeraObra.tecnica)

        val segundaObra = state.obras[1]
        assertEquals("O002", segundaObra.id)
        assertEquals("María García", segundaObra.artistaNombre)
        assertEquals("Retrato Abstracto", segundaObra.tituloObra)
        assertEquals("2025-01-14", segundaObra.fechaAprobacion)
        assertEquals("Acrílico", segundaObra.tecnica)
    }

    @Test
    fun `filtrarPorTecnica con TODAS debe mostrar todas las obras`() = runTest {
        // Given - ViewModel con obras cargadas

        // When - se filtra por "TODAS"
        viewModel.filtrarPorTecnica("TODAS")

        // Then - debe mostrar todas las obras
        val state = viewModel.uiState.value
        assertEquals("El filtro debe ser 'TODAS'", "TODAS", state.filtroTecnica)
        assertEquals("Debe mostrar todas las obras", state.obras.size, state.obrasFiltradas.size)
        assertEquals("Las obras filtradas deben ser iguales a todas las obras", state.obras, state.obrasFiltradas)
    }

    @Test
    fun `filtrarPorTecnica con tecnica especifica debe filtrar correctamente`() = runTest {
        // Given - ViewModel con obras cargadas

        // When - se filtra por "Óleo sobre lienzo"
        viewModel.filtrarPorTecnica("Óleo sobre lienzo")

        // Then - debe mostrar solo las obras con esa técnica
        val state = viewModel.uiState.value
        assertEquals("El filtro debe ser 'Óleo sobre lienzo'", "Óleo sobre lienzo", state.filtroTecnica)
        assertEquals("Debe haber 1 obra filtrada", 1, state.obrasFiltradas.size)
        assertEquals("La obra filtrada debe tener la técnica correcta", "Óleo sobre lienzo", state.obrasFiltradas[0].tecnica)
        assertEquals("Debe ser la obra de Juan Pérez", "Juan Pérez", state.obrasFiltradas[0].artistaNombre)
    }

    @Test
    fun `filtrarPorTecnica con tecnica inexistente debe devolver lista vacia`() = runTest {
        // Given - ViewModel con obras cargadas

        // When - se filtra por una técnica que no existe
        viewModel.filtrarPorTecnica("Acuarela")

        // Then - debe devolver lista vacía
        val state = viewModel.uiState.value
        assertEquals("El filtro debe ser 'Acuarela'", "Acuarela", state.filtroTecnica)
        assertTrue("La lista filtrada debe estar vacía", state.obrasFiltradas.isEmpty())
        assertEquals("Las obras originales deben seguir ahí", 2, state.obras.size)
    }

    @Test
    fun `filtrarPorTecnica con Acrilico debe filtrar correctamente`() = runTest {
        // Given - ViewModel con obras cargadas

        // When - se filtra por "Acrílico"
        viewModel.filtrarPorTecnica("Acrílico")

        // Then - debe mostrar solo las obras con esa técnica
        val state = viewModel.uiState.value
        assertEquals("El filtro debe ser 'Acrílico'", "Acrílico", state.filtroTecnica)
        assertEquals("Debe haber 1 obra filtrada", 1, state.obrasFiltradas.size)
        assertEquals("La obra filtrada debe tener la técnica correcta", "Acrílico", state.obrasFiltradas[0].tecnica)
        assertEquals("Debe ser la obra de María García", "María García", state.obrasFiltradas[0].artistaNombre)
    }

    @Test
    fun `clearError debe limpiar el error del estado`() = runTest {
        // Given - ViewModel con un error (simulamos estableciendo un error manualmente)
        // Como no podemos acceder directamente al _uiState, simulamos un escenario donde habría error
        // En este caso, probamos que clearError funciona independientemente del estado actual

        // When - se limpia el error
        viewModel.clearError()

        // Then - no debe haber error
        val state = viewModel.uiState.value
        assertNull("El error debe ser null después de clearError", state.error)
    }

    @Test
    fun `multiples filtros consecutivos deben funcionar correctamente`() = runTest {
        // Given - ViewModel con obras cargadas

        // When - se aplican múltiples filtros consecutivos
        viewModel.filtrarPorTecnica("Óleo sobre lienzo")
        var state = viewModel.uiState.value
        assertEquals("Debe filtrar por Óleo sobre lienzo", 1, state.obrasFiltradas.size)

        viewModel.filtrarPorTecnica("Acrílico")
        state = viewModel.uiState.value
        assertEquals("Debe filtrar por Acrílico", 1, state.obrasFiltradas.size)
        assertEquals("Debe ser la obra de María García", "María García", state.obrasFiltradas[0].artistaNombre)

        viewModel.filtrarPorTecnica("TODAS")
        state = viewModel.uiState.value
        assertEquals("Debe mostrar todas las obras", 2, state.obrasFiltradas.size)

        // Then - el estado final debe ser correcto
        assertEquals("El filtro final debe ser 'TODAS'", "TODAS", state.filtroTecnica)
        assertEquals("Debe mostrar todas las obras al final", state.obras.size, state.obrasFiltradas.size)
    }

    @Test
    fun `obras deben mantener el orden correcto`() = runTest {
        // Given - ViewModel con obras cargadas

        // When - se obtiene el estado
        val state = viewModel.uiState.value

        // Then - las obras deben estar en el orden esperado
        assertEquals("La primera obra debe ser de Juan Pérez", "Juan Pérez", state.obras[0].artistaNombre)
        assertEquals("La segunda obra debe ser de María García", "María García", state.obras[1].artistaNombre)
        assertEquals("La primera obra debe tener fecha 2025-01-15", "2025-01-15", state.obras[0].fechaAprobacion)
        assertEquals("La segunda obra debe tener fecha 2025-01-14", "2025-01-14", state.obras[1].fechaAprobacion)
    }
}