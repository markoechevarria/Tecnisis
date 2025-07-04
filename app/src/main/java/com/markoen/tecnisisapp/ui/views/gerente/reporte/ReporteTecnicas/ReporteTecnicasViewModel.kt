package com.markoen.tecnisisapp.ui.views.gerente.reporte.ReporteTecnicas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class ReporteTecnicasViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {

    private val _uiState = MutableStateFlow(ReporteTecnicasUiState() )
    val uiState: StateFlow<ReporteTecnicasUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }
        obtenerReporteTecnicas()
    }

    fun obtenerReporteTecnicas() {
        try {
            viewModelScope.launch {
                val reporte: List<TecnicaReporte> = usuarioRepository.obtenerReporteTecnica()
                _uiState.update { currentState -> currentState.copy( tecnicasLista = reporte ) }
            }
        } catch (e: Exception) {

        }
    }
}