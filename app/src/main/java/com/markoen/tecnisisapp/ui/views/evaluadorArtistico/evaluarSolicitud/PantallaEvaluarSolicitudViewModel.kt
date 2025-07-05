package com.markoen.tecnisisapp.ui.views.evaluadorArtistico.evaluarSolicitud

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import com.markoen.tecnisisapp.ui.views.login.LoginScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaEvaluarSolicitudViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(PantallaEvaluarSolicitudUiState() )
    val uiState: StateFlow<PantallaEvaluarSolicitudUiState> = _uiState.asStateFlow()

    fun cambiarVentanaAprobado() {
        var valor: Boolean = _uiState.value.showDialogAprobado
        _uiState.update { currentState -> currentState.copy( showDialogAprobado = !valor ) }
    }

    fun cambiarVentanaDesaprobado() {
        var valor: Boolean = _uiState.value.showDialogDesaprobado
        _uiState.update { currentState -> currentState.copy( showDialogDesaprobado = !valor ) }
    }

    fun asignarAprobacion( aprobado: Boolean ) {
        _uiState.update { currentState -> currentState.copy( aprobado = aprobado ) }
        evaluarSolicitud()
    }

    fun asignarIds(id: Int, id_perfil: Int, id_solicitud: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil, id_solicitud = id_solicitud ) }
        viewModelScope.launch {
            try {
                val solicitud = usuarioRepository.obtenerSolicitudPorId(id_solicitud)
                val obra = usuarioRepository.obtenerObra(solicitud.id_obra)
                _uiState.update { currentState -> currentState.copy( url_obra = obra.imagen_obra ) }
            } catch (e: Exception) {
                Log.d("viewmodelasignaridsevaluarsolicitud", "aca se obtienen la url de la imagen: ${e.message}")
            } finally {
                _uiState.update { currrentState -> currrentState.copy(isLoading = false) }
            }
        }
    }

    fun evaluarSolicitud() {
        viewModelScope.launch {
            try {
                usuarioRepository.evaluarSolicitudArtistico( _uiState.value.id_solicitud, (if ( _uiState.value.aprobado ) { 1 } else { 0 }) )
                Log.d("viewmodelevaluacion", "se llamo a la api para hacer la evaluacion")
            } catch (e: Exception) {
                Log.d("viewmodelevaluacion", e.message.toString())
            }
        }
    }
}