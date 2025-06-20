package com.example.tecnisis.ui.test

import com.example.tecnisis.ui.casosDeUso.anfitrion.registrarArtista.RegistrarArtistaViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegistrarArtistaViewModelTest {
    private lateinit var viewModel: RegistrarArtistaViewModel

    @Before
    fun setup() {
        viewModel = RegistrarArtistaViewModel()
    }

    @Test
    fun registrarArtistaViewModel_Initialization_DefaultState() {
        val uiState = viewModel.uiState.value
        assertEquals("", uiState.dni)
        assertEquals("", uiState.nombre)
        assertEquals("", uiState.direccion)
        assertEquals("", uiState.telefono)
        assertEquals("", uiState.correo)
        assertFalse(uiState.habilitadoBoton)
    }

    @Test
    fun registrarArtistaViewModel_UpdateFields_EnablesButton() {
        viewModel.actualizarDni("12345678")
        viewModel.actualizarNombre("Juan Perez")
        viewModel.actualizarDireccion("Calle 1")
        viewModel.actualizarTelefono("987654321")
        viewModel.actualizarCorreo("juan@correo.com")
        val uiState = viewModel.uiState.value
        assertTrue(uiState.habilitadoBoton)
    }

    @Test
    fun registrarArtistaViewModel_PartialFields_DisablesButton() {
        viewModel.actualizarDni("12345678")
        viewModel.actualizarNombre("")
        viewModel.actualizarDireccion("Calle 1")
        viewModel.actualizarTelefono("987654321")
        viewModel.actualizarCorreo("juan@correo.com")
        val uiState = viewModel.uiState.value
        assertFalse(uiState.habilitadoBoton)
    }
}