package com.markoen.tecnisisapp.ui.views.gerente.reporte.ReporteArtistas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoen.tecnisisapp.domain.models.ArtistaReporte
import com.markoen.tecnisisapp.domain.models.ExpertoSolicitudesReporte
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReporteArtistasViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {

    private val _uiState = MutableStateFlow(ReporteArtistasUiState() )
    val uiState: StateFlow<ReporteArtistasUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }
        obtenerReporteArtistas()
    }

    fun obtenerReporteArtistas() {
        try {
            viewModelScope.launch {
                val reporte: List<ArtistaReporte> = usuarioRepository.obtenerReporteArtistaPrecios()
                Log.d("viewmodelreporteartista", reporte.toString())
                _uiState.update { currentState -> currentState.copy( ArtistasLista = reporte ) }
            }
        } catch (e: Exception) {
            Log.d("viewmodelreporteartista", e.message.toString())
        }
    }
}