package com.example.tecnisis.ui.test

import com.example.tecnisis.ui.views.anfitrion.registrarObra.RegistrarObraViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegistrarObraViewModelTest {

    private lateinit var viewModel: RegistrarObraViewModel

    @Before
    fun setUp() {
        viewModel = RegistrarObraViewModel()
    }

    @Test
    fun `uiState inicial tiene valores por defecto`() {
        // Given - ViewModel inicializado

        // When - obtenemos estado inicial
        val estadoInicial = viewModel.uiState.value

        // Then - verificamos valores por defecto
        assertEquals("", estadoInicial.nombre)
        assertEquals("", estadoInicial.tecnica)
        assertEquals("", estadoInicial.fecha)
        assertEquals("", estadoInicial.dimensiones)
        assertFalse(estadoInicial.habilitadoBoton)
    }

    @Test
    fun `actualizarNombre actualiza el nombre correctamente`() {
        // Given - ViewModel inicializado
        val nombreTest = "Mona Lisa"

        // When - actualizamos nombre
        viewModel.actualizarNombre(nombreTest)

        // Then - verificamos que se actualizó
        val estado = viewModel.uiState.value
        assertEquals(nombreTest, estado.nombre)
        // Botón debe seguir deshabilitado porque faltan otros campos
        assertFalse(estado.habilitadoBoton)
    }

    @Test
    fun `actualizarTecnica actualiza la tecnica correctamente`() {
        // Given - ViewModel inicializado
        val tecnicaTest = "Óleo sobre lienzo"

        // When - actualizamos tecnica
        viewModel.actualizarTecnica(tecnicaTest)

        // Then - verificamos que se actualizó
        val estado = viewModel.uiState.value
        assertEquals(tecnicaTest, estado.tecnica)
        assertFalse(estado.habilitadoBoton)
    }

    @Test
    fun `actualizarFecha actualiza la fecha correctamente`() {
        // Given - ViewModel inicializado
        val fechaTest = "2025-06-20"

        // When - actualizamos fecha
        viewModel.actualizarFecha(fechaTest)

        // Then - verificamos que se actualizó
        val estado = viewModel.uiState.value
        assertEquals(fechaTest, estado.fecha)
        assertFalse(estado.habilitadoBoton)
    }

    @Test
    fun `actualizarDimensiones actualiza las dimensiones correctamente`() {
        // Given - ViewModel inicializado
        val dimensionesTest = "100x80 cm"

        // When - actualizamos dimensiones
        viewModel.actualizarDimensiones(dimensionesTest)

        // Then - verificamos que se actualizó
        val estado = viewModel.uiState.value
        assertEquals(dimensionesTest, estado.dimensiones)
        assertFalse(estado.habilitadoBoton)
    }

    @Test
    fun `habilitarBoton habilita boton cuando todos los campos estan completos`() {
        // Given - ViewModel inicializado

        // When - completamos todos los campos
        viewModel.actualizarNombre("Mona Lisa")
        viewModel.actualizarTecnica("Óleo sobre lienzo")
        viewModel.actualizarFecha("2025-06-20")
        viewModel.actualizarDimensiones("100x80 cm")

        // Then - verificamos que el botón está habilitado
        val estado = viewModel.uiState.value
        assertTrue(estado.habilitadoBoton)
        assertEquals("Mona Lisa", estado.nombre)
        assertEquals("Óleo sobre lienzo", estado.tecnica)
        assertEquals("2025-06-20", estado.fecha)
        assertEquals("100x80 cm", estado.dimensiones)
    }

    @Test
    fun `boton permanece deshabilitado si falta el nombre`() {
        // Given - ViewModel inicializado

        // When - completamos todos los campos excepto nombre
        viewModel.actualizarTecnica("Óleo sobre lienzo")
        viewModel.actualizarFecha("2025-06-20")
        viewModel.actualizarDimensiones("100x80 cm")

        // Then - verificamos que el botón permanece deshabilitado
        assertFalse(viewModel.uiState.value.habilitadoBoton)
    }

    @Test
    fun `boton permanece deshabilitado si falta la tecnica`() {
        // Given - ViewModel inicializado

        // When - completamos todos los campos excepto tecnica
        viewModel.actualizarNombre("Mona Lisa")
        viewModel.actualizarFecha("2025-06-20")
        viewModel.actualizarDimensiones("100x80 cm")

        // Then - verificamos que el botón permanece deshabilitado
        assertFalse(viewModel.uiState.value.habilitadoBoton)
    }

    @Test
    fun `boton permanece deshabilitado si falta la fecha`() {
        // Given - ViewModel inicializado

        // When - completamos todos los campos excepto fecha
        viewModel.actualizarNombre("Mona Lisa")
        viewModel.actualizarTecnica("Óleo sobre lienzo")
        viewModel.actualizarDimensiones("100x80 cm")

        // Then - verificamos que el botón permanece deshabilitado
        assertFalse(viewModel.uiState.value.habilitadoBoton)
    }

    @Test
    fun `boton permanece deshabilitado si faltan las dimensiones`() {
        // Given - ViewModel inicializado

        // When - completamos todos los campos excepto dimensiones
        viewModel.actualizarNombre("Mona Lisa")
        viewModel.actualizarTecnica("Óleo sobre lienzo")
        viewModel.actualizarFecha("2025-06-20")

        // Then - verificamos que el botón permanece deshabilitado
        assertFalse(viewModel.uiState.value.habilitadoBoton)
    }


    @Test
    fun `actualizaciones secuenciales mantienen otros campos intactos`() {
        // Given - ViewModel inicializado

        // When - actualizamos campos uno por uno
        viewModel.actualizarNombre("Mona Lisa")
        val estadoDespuesNombre = viewModel.uiState.value

        viewModel.actualizarTecnica("Óleo sobre lienzo")
        val estadoDespuesTecnica = viewModel.uiState.value

        viewModel.actualizarFecha("2025-06-20")
        val estadoDespuesFecha = viewModel.uiState.value

        // Then - verificamos que cada actualización mantuvo los campos anteriores
        assertEquals("Mona Lisa", estadoDespuesNombre.nombre)
        assertEquals("", estadoDespuesNombre.tecnica)

        assertEquals("Mona Lisa", estadoDespuesTecnica.nombre)
        assertEquals("Óleo sobre lienzo", estadoDespuesTecnica.tecnica)
        assertEquals("", estadoDespuesTecnica.fecha)

        assertEquals("Mona Lisa", estadoDespuesFecha.nombre)
        assertEquals("Óleo sobre lienzo", estadoDespuesFecha.tecnica)
        assertEquals("2025-06-20", estadoDespuesFecha.fecha)
        assertEquals("", estadoDespuesFecha.dimensiones)
    }

    @Test
    fun `boton se habilita inmediatamente al completar el ultimo campo`() {
        // Given - tres campos completos, uno vacío
        viewModel.actualizarNombre("Mona Lisa")
        viewModel.actualizarTecnica("Óleo sobre lienzo")
        viewModel.actualizarFecha("2025-06-20")

        // Verificamos que está deshabilitado
        assertFalse(viewModel.uiState.value.habilitadoBoton)

        // When - completamos el último campo
        viewModel.actualizarDimensiones("100x80 cm")

        // Then - verificamos que se habilita inmediatamente
        assertTrue(viewModel.uiState.value.habilitadoBoton)
    }
}