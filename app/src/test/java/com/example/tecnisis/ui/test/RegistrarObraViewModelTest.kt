package com.example.tecnisis.ui.test

import com.example.tecnisis.ui.casosDeUso.anfitrion.registrarObra.RegistrarObraViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegistrarObraViewModelTest {
    private lateinit var viewModel: RegistrarObraViewModel

    @Before
    fun setup() {
        viewModel = RegistrarObraViewModel()
    }

    @Test
    fun registrarObraViewModel_Initialization_DefaultState() {
        val uiState = viewModel.uiState.value
        assertEquals("", uiState.nombre)
        assertEquals("", uiState.fecha)
        assertEquals("", uiState.tecnica)
        assertEquals("", uiState.dimensiones)
        assertFalse(uiState.habilitadoBoton)
    }

    @Test
    fun registrarObraViewModel_UpdateFields_EnablesButton() {
        viewModel.actualizarNombre("Obra 1")
        viewModel.actualizarFecha("2025-01-01")
        viewModel.actualizarTecnica("Óleo")
        viewModel.actualizarDimensiones("100x100")
        val uiState = viewModel.uiState.value
        assertTrue(uiState.habilitadoBoton)
    }

    @Test
    fun registrarObraViewModel_PartialFields_DisablesButton() {
        viewModel.actualizarNombre("Obra 1")
        viewModel.actualizarFecha("")
        viewModel.actualizarTecnica("Óleo")
        viewModel.actualizarDimensiones("100x100")
        val uiState = viewModel.uiState.value
        assertFalse(uiState.habilitadoBoton)
    }
}