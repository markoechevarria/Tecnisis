package com.markoen.tecnisisapp.ui.views.anfitrion.listarExpertosDisponibles

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Usuario
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListarExpertosDisponiblesViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListarExpertosDisponiblesUiState() )
    val uiState: StateFlow<ListarExpertosDisponiblesUiState> = _uiState.asStateFlow()

    fun obtenerDatosSolicitud(id: Int, id_perfil: Int, id_artista: Int, id_obra: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }
        viewModelScope.launch {
            try {
                val obra: Obra = usuarioRepository.obtenerObra( id = id_obra )
                val evaluadoresArtisticos = usuarioRepository.listarEvaluadoresArtisticos()
                val artista: Artista = usuarioRepository.buscarArtistaId( id = id_artista )
                _uiState.update { currentState -> currentState.copy( obra = obra, artista = artista, listaEvaluadoresArtisticos = evaluadoresArtisticos ) }
            } catch (e: Exception) {
                Log.d("viewmodelobtenerDatosSolicitud", "aca se obtienen los datos de solicitud: ${e.message}")
            } finally {
                _uiState.update { currentState -> currentState.copy( isLoading = false ) }
            }
        }
    }

    fun seleccionarExperto(id: Int) {
        _uiState.update { currentState ->
            currentState.copy( expertoSeleccionadoId = id, habilitadoBotonExpertoSeleccionado = true )
        }
    }
}