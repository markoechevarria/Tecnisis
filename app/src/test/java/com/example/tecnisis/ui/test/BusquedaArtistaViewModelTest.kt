package com.example.tecnisis.ui.test

import com.example.tecnisis.ui.views.anfitrion.busquedaArtista.BusquedaArtistaViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BusquedaArtistaViewModelTest {
    private lateinit var viewModel: BusquedaArtistaViewModel

    @Before
    fun setup() {
        viewModel = BusquedaArtistaViewModel()
    }

    @Test
    fun busquedaArtistaViewModel_Initialization_DefaultState() {
        val uiState = viewModel.uiState.value
        assertEquals("", uiState.dni)
        assertFalse(uiState.seEncontro)
        assertEquals("", uiState.nombreArtista)
        assertFalse(uiState.habilitadoBotonObra)
        assertFalse(uiState.habilitadoBotonArtista)
    }

    @Test
    fun busquedaArtistaViewModel_BuscaArtistaExistente_ActivaBotonObra() {
        // Usar un DNI de la lista real del ViewModel
        val dniEjemplo = viewModel.uiState.value.listaArtistas.first().first
        val nombreEsperado = viewModel.uiState.value.listaArtistas.first().second
        viewModel.actualizarDni(dniEjemplo)
        val uiState = viewModel.uiState.value
        assertTrue(uiState.seEncontro)
        assertEquals(nombreEsperado, uiState.nombreArtista)
        assertTrue(uiState.habilitadoBotonObra)
    }

    @Test
    fun busquedaArtistaViewModel_BuscaArtistaInexistente_ActivaBotonArtista() {
        viewModel.actualizarDni("00000000")
        val uiState = viewModel.uiState.value
        assertFalse(uiState.seEncontro)
        assertTrue(uiState.habilitadoBotonArtista)
    }


    @Test
    fun busquedaArtistaViewModel_ArtistaEncontrado_StateUpdatedCorrectly() {
        // Given - DNI existente en la lista
        val dniExistente = "11100011"
        val nombreEsperado = "Juan"

        // When
        viewModel.actualizarDni(dniExistente)

        // Then
        val uiState = viewModel.uiState.value
        assertEquals(dniExistente, uiState.dni)
        assertEquals(nombreEsperado, uiState.nombreArtista)
        assertTrue(uiState.seEncontro)
        assertTrue(uiState.habilitadoBotonObra)
        assertFalse(uiState.habilitadoBotonArtista)
    }

    @Test
    fun busquedaArtistaViewModel_ArtistaNoEncontrado_StateUpdatedCorrectly() {
        // Given - DNI que no existe en la lista
        val dniNoExistente = "99999999"

        // When
        viewModel.actualizarDni(dniNoExistente)

        // Then
        val uiState = viewModel.uiState.value
        assertEquals(dniNoExistente, uiState.dni)
        assertEquals("", uiState.nombreArtista)
        assertFalse(uiState.seEncontro)
        assertFalse(uiState.habilitadoBotonObra)
        assertTrue(uiState.habilitadoBotonArtista)
    }

    @Test
    fun busquedaArtistaViewModel_MultipleArtistasEncontrados_FirstMatchUsed() {
        // Given - Todos los artistas en la lista
        val artistas = listOf(
            "11100011" to "Juan",
            "22233322" to "Percy",
            "12345678" to "Marko"
        )

        // When/Then - Probar cada artista
        artistas.forEach { (dni, nombreEsperado) ->
            viewModel.actualizarDni(dni)
            val uiState = viewModel.uiState.value

            assertEquals(dni, uiState.dni)
            assertEquals(nombreEsperado, uiState.nombreArtista)
            assertTrue(uiState.seEncontro)
            assertTrue(uiState.habilitadoBotonObra)
            assertFalse(uiState.habilitadoBotonArtista)
        }
    }

    @Test
    fun busquedaArtistaViewModel_EmptyDni_NoMatch() {
        // Given - DNI vacío
        val dniVacio = ""

        // When
        viewModel.actualizarDni(dniVacio)

        // Then
        val uiState = viewModel.uiState.value
        assertEquals("", uiState.dni)
        assertEquals("", uiState.nombreArtista)
        assertFalse(uiState.seEncontro)
        assertFalse(uiState.habilitadoBotonObra)
        assertTrue(uiState.habilitadoBotonArtista)
    }

    @Test
    fun busquedaArtistaViewModel_PartialDniMatch_NoMatch() {
        // Given - DNI parcial que debería hacer match por contains() pero no por equals()
        val dniParcial = "111"

        // When
        viewModel.actualizarDni(dniParcial)

        // Then - La lógica actual usa contains() en any() pero equals() en find()
        val uiState = viewModel.uiState.value
        assertEquals(dniParcial, uiState.dni)
        assertEquals("", uiState.nombreArtista) // No debería encontrar porque find() usa equals()
        assertFalse(uiState.seEncontro)
        assertFalse(uiState.habilitadoBotonObra)
        assertTrue(uiState.habilitadoBotonArtista)
    }
}