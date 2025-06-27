package com.example.tecnisis.ui.casosDeUso.anfitrion.busquedaArtista

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusquedaArtistaViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(BusquedaArtistaUiState() )
    val uiState: StateFlow<BusquedaArtistaUiState> = _uiState.asStateFlow()

    fun actualizarDni( dni: String) {
        _uiState.update { currentState -> currentState.copy( dni = dni) }
        actualizarSeEncontroNombreArtista()
        actualizarOpciones()
    }

    fun actualizarSeEncontroNombreArtista() {
        viewModelScope.launch {
            try {
                Log.d("viewmodelBusqeudaArtista", "entrando al try")
                val Artista = usuarioRepository.buscarArtistaDni( _uiState.value.dni)
                Log.d("viewmodelBusqeudaArtista", "despues de hacer la llamada a la api")
                _uiState.update { currentState -> currentState.copy(
                    id = Artista.id,
                    dni = Artista.dni,
                    nombreArtista = Artista.nombre,
                    seEncontro = true,
                ) }
            } catch (e: Exception) {
                Log.d("viewmodelBusqeudaArtista", "entro y agarro al catch")
                _uiState.update { currentState -> currentState.copy(
                    seEncontro = false
                ) }
                Log.d("viewmodel", e.message.toString())
            }
        }
    }

    fun actualizarOpciones() {
        if (_uiState.value.seEncontro) {
            _uiState.update { currentState -> currentState.copy(habilitadoBotonObra = true) }
            _uiState.update { currentState -> currentState.copy( habilitadoBotonArtista = false) }
        } else {
            _uiState.update { currentState -> currentState.copy(habilitadoBotonObra = false) }
            _uiState.update { currentState -> currentState.copy( habilitadoBotonArtista = true) }
        }
    }
}