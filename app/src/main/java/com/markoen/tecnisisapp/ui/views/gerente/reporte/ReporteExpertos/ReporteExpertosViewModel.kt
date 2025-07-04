package com.markoen.tecnisisapp.ui.views.gerente.reporte.ReporteExpertos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoen.tecnisisapp.domain.models.ExpertoSolicitudesReporte
import com.markoen.tecnisisapp.domain.models.TecnicaReporte
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReporteExpertosViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReporteExpertosUiState())
    val uiState: StateFlow<ReporteExpertosUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy(id = id, id_perfil = id_perfil) }
        obtenerReporteExpertos()
    }

    fun obtenerReporteExpertos() {
        try {
            viewModelScope.launch {
                val reporte: List<ExpertoSolicitudesReporte> = usuarioRepository.obtenerReporteExpertoSolicitudes()
                _uiState.update { currentState -> currentState.copy( ExpertosLista = reporte ) }
            }
        } catch (e: Exception) {

        }
    }
}