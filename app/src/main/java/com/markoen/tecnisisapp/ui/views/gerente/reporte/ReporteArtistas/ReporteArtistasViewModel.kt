package com.markoen.tecnisisapp.ui.views.gerente.reporte.ReporteArtistas

import androidx.lifecycle.ViewModel
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ReporteArtistasViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {

    private val _uiState = MutableStateFlow(ReporteArtistasUiState() )
    val uiState: StateFlow<ReporteArtistasUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }
    }
}