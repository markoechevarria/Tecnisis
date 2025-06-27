package com.example.tecnisis.ui.casosDeUso.anfitrion.solicitudesRegistradasAnfitrion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Solicitud
import com.example.tecnisis.domain.models.Usuario
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolicitudesRegistradasViewModelAnfitrion @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SolicitudesRegistradasUiStateAnfitrion() )
    val uiState: StateFlow<SolicitudesRegistradasUiStateAnfitrion> = _uiState.asStateFlow()

    init {
        obtenerSolicitudes()
    }

    fun obtenerSolicitudes() {
        viewModelScope.launch {
            try {
                Log.d("viewmodelListarSolicitiudes", "entrando al try")
                val solicitudes: List<Solicitud> = usuarioRepository.obtenerSolicitudes()
                Log.d("viewmodelListarSolicitiudes", "despues de hacer la llamada a la api")
                _uiState.update { currentState -> currentState.copy( listaSolicitudes = solicitudes ) }
            } catch(e: Exception) {
                Log.d("viewmodelListarSolicitiudes", "entro y agarro al catch")
                Log.d("viewmodelListarSolicitiudes", e.message.toString())
            }
        }
    }

    fun obtenerDatosSolicitudIndividual(soli: Solicitud) {
        obtenerDatosObra(soli.id_obra)
        obtenerDatosArtista(soli.id_artista)
        obtenerDatosEvaluadorArtistico(soli.id_evaluador_artistico)
    }

    fun obtenerDatosObra(id: Int) {
        viewModelScope.launch {
            try {
                val datosObra: Obra = usuarioRepository.obtenerObra(id)
                _uiState.update { currentState -> currentState.copy( obra = datosObra ) }
            } catch(e: Exception) {
                Log.d("viewmodelListarSolicitiudes", "entro y agarro al catch")
                Log.d("viewmodelListarSolicitiudes", e.message.toString())
            }
        }
    }
    fun obtenerDatosArtista(id: Int) {
        viewModelScope.launch {
            try {
                val datosArtista: Artista = usuarioRepository.buscarArtistaId(id)
                _uiState.update { currentState -> currentState.copy( artista = datosArtista ) }
            } catch(e: Exception) {
                Log.d("viewmodelListarSolicitiudes", "entro y agarro al catch")
                Log.d("viewmodelListarSolicitiudes", e.message.toString())
            }
        }
    }
    fun obtenerDatosEvaluadorArtistico(id: Int) {
        viewModelScope.launch {
            try {
                val datosEvaluadorArtistico: Usuario = usuarioRepository.obtenerUsuario(id)
                _uiState.update { currentState -> currentState.copy( evaluador_artistico = datosEvaluadorArtistico ) }
            } catch(e: Exception) {
                Log.d("viewmodelListarSolicitiudes", "entro y agarro al catch")
                Log.d("viewmodelListarSolicitiudes", e.message.toString())
            }
        }
    }
}