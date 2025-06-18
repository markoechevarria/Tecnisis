package com.example.tecnisis.ui.casosDeUso.login.login

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
import com.example.tecnisis.data.UserPreferences

class LoginScreenViewModel(private val userPreferences: UserPreferences) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val api = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/") // Para el emulador de Android
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginApi::class.java)

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun login() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val response = api.login(LoginRequest(_uiState.value.email, _uiState.value.password))
                if (response.success && response.id_usuario != null && response.tipo_usuario != null) {
                    // Guardar sesión en DataStore
                    userPreferences.saveUserSession(response.id_usuario, response.tipo_usuario)
                    _uiState.update {
                        it.copy(
                            isLoginSuccessful = true,
                            idUsuario = response.id_usuario,
                            tipoUsuario = response.tipo_usuario,
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            error = "Credenciales inválidas",
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = "Error de conexión: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

interface LoginApi {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val id_usuario: String?,
    val correo: String?,
    val tipo_usuario: String?
)