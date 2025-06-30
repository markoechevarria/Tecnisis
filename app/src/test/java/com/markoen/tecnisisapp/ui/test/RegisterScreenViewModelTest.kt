package com.markoen.tecnisisapp.ui.test

import com.markoen.tecnisisapp.ui.views.register.register.RegisterScreenViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegisterScreenViewModelTest {
    private lateinit var viewModel: RegisterScreenViewModel

    @Before
    fun setup() {
        viewModel = RegisterScreenViewModel()
    }

    @Test
    fun registerScreenViewModel_Initialization_DefaultState() {
        val uiState = viewModel.uiState.value
        assertEquals("", uiState.nombre)
        assertEquals("", uiState.dni)
        assertEquals("", uiState.telefono)
        assertEquals("", uiState.correo)
        assertEquals("", uiState.password)
        assertFalse(uiState.isLoading)
        assertNull(uiState.error)
        assertFalse(uiState.isRegisterSuccessful)
    }

    @Test
    fun registerScreenViewModel_UpdateFields_UpdatesUiState() {
        viewModel.updateNombre("Juan Perez")
        viewModel.updateDni("12345678")
        viewModel.updateTelefono("987654321")
        viewModel.updateCorreo("juan@correo.com")
        viewModel.updatePassword("password123")
        val uiState = viewModel.uiState.value
        assertEquals("Juan Perez", uiState.nombre)
        assertEquals("12345678", uiState.dni)
        assertEquals("987654321", uiState.telefono)
        assertEquals("juan@correo.com", uiState.correo)
        assertEquals("password123", uiState.password)
    }

    @Test
    fun registerScreenViewModel_ClearError_SetsErrorNull() {
        viewModel.updateNombre("Juan Perez")
        // Simula un error
        viewModel.uiState.value.copy(error = "Error de prueba")
        viewModel.clearError()
        val uiState = viewModel.uiState.value
        assertNull(uiState.error)
    }
}