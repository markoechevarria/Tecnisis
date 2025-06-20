package com.example.tecnisis.ui.casosDeUso.register.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class RegisterScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val api = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/") // Cambia esto si usas un servidor real
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RegisterApi::class.java)

    fun updateNombre(nombre: String) {
        _uiState.update { it.copy(nombre = nombre) }
    }
    fun updateDni(dni: String) {
        _uiState.update { it.copy(dni = dni) }
    }
    fun updateTelefono(telefono: String) {
        _uiState.update { it.copy(telefono = telefono) }
    }
    fun updateCorreo(correo: String) {
        _uiState.update { it.copy(correo = correo) }
    }
    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun register() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val response = api.register(
                    RegisterRequest(
                        nombre = _uiState.value.nombre,
                        dni = _uiState.value.dni,
                        telefono = _uiState.value.telefono,
                        correo = _uiState.value.correo,
                        password = _uiState.value.password
                    )
                )
                if (response.success) {
                    _uiState.update { it.copy(isRegisterSuccessful = true, isLoading = false) }
                } else {
                    _uiState.update { it.copy(error = response.message, isLoading = false) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Error de conexión: ${e.message}", isLoading = false) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

interface RegisterApi {
    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}

data class RegisterRequest(
    val nombre: String,
    val dni: String,
    val telefono: String,
    val correo: String,
    val password: String
)

data class RegisterResponse(
    val success: Boolean,
    val message: String?
) 