package com.example.tecnisis.ui.test

import com.example.tecnisis.ui.casosDeUso.anfitrion.busquedaArtista.BusquedaArtistaViewModel
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
}