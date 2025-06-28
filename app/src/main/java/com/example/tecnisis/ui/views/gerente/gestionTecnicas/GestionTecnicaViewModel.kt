package com.example.tecnisis.ui.views.gerente.gestionTecnicas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.domain.models.Tecnica
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GestionTecnicaViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(GestionTecnicaUiState() )
    val uiState: StateFlow<GestionTecnicaUiState> = _uiState.asStateFlow()

    fun actualizarDatos( id: Int, id_perfil: Int ) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }

        viewModelScope.launch {
            try {
                val tecnicas: List<Tecnica> = usuarioRepository.obtenerTecnicas()
                _uiState.update { currentState -> currentState.copy( listaTecnica = tecnicas ) }
            } catch (e: Exception) {
                Log.d("viewmodelSolicitudesRegistradasRevisarEconomico", "entro y agarro al catch")
                Log.d("viewmodelSolicitudesRegistradasRevisarEconomico", e.message.toString())
            }
        }
    }
    fun editarDatos() {}
    fun eliminarDatos() {}
}