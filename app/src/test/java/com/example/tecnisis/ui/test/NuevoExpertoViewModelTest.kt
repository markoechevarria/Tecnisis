package com.example.tecnisis.ui.test

import com.example.tecnisis.ui.views.gerente.nuevoExperto.NuevoExpertoViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NuevoExpertoViewModelTest {
    private lateinit var viewModel: NuevoExpertoViewModel

    @Before
    fun setup() {
        viewModel = NuevoExpertoViewModel()
    }

    @Test
    fun nuevoExpertoViewModel_Initialization_DefaultState() {
        val uiState = viewModel.uiState.value
        assertEquals("", uiState.dni)
        assertEquals("", uiState.nombre)
        assertEquals("", uiState.direccion)
        assertEquals("", uiState.telefono)
        assertEquals("", uiState.correo)
        assertFalse(uiState.habilitadoBoton)
    }

    @Test
    fun nuevoExpertoViewModel_UpdateFields_EnablesButton() {
        viewModel.actualizarDni("12345678")
        viewModel.actualizarNombre("Ana Torres")
        viewModel.actualizarDireccion("Calle 2")
        viewModel.actualizarTelefono("987654321")
        viewModel.actualizarCorreo("ana@correo.com")
        val uiState = viewModel.uiState.value
        assertTrue(uiState.habilitadoBoton)
    }

    @Test
    fun nuevoExpertoViewModel_PartialFields_DisablesButton() {
        viewModel.actualizarDni("12345678")
        viewModel.actualizarNombre("")
        viewModel.actualizarDireccion("Calle 2")
        viewModel.actualizarTelefono("987654321")
        viewModel.actualizarCorreo("ana@correo.com")
        val uiState = viewModel.uiState.value
        assertFalse(uiState.habilitadoBoton)
    }
}