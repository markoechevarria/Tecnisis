package com.markoen.tecnisisapp.ui.views.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Solicitud
import com.markoen.tecnisisapp.domain.models.Tecnica
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SolicitudesRegistradasViewModelEvaluadorArtistico @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SolicitudesRegistradasUiStateEvaluadorArtistico() )
    val uiState: StateFlow<SolicitudesRegistradasUiStateEvaluadorArtistico> = _uiState.asStateFlow()

    fun actualizarDatos( id: Int, idPerfil: Int ) {
        _uiState.update { currentState -> currentState.copy( id = id, idPerfil = idPerfil ) }

        viewModelScope.launch {
            try {
                val solicitudes = usuarioRepository.obtenerSolicitudesEvaluadorArtistico(_uiState.value.id).filter { it.aprobadaEvaluadorArtistico == false }
                _uiState.update { currentState -> currentState.copy( listaSolicitudes = solicitudes ) }

            } catch (e: Exception) {
                Log.d("viewmodelSolicitudesregsitradas", "entro y agarro al catch")
                Log.d("viewmodelSolicitudesregsitradas", e.message.toString())
            }
        }
    }

    fun obtenerDatosExtra( solic: Solicitud ) {
        viewModelScope.launch {
            try {
                Log.d("viewmodelsolicitudesregistradas", "se llamo a la api para obtener datos extra: ${solic}")
                val id_solicitud: Int = solic.id
                Log.d("viewmodelsolicitudesregistradas", "se llamo a la api para obtener datos extra: ${id_solicitud}")
                val artista: Artista = usuarioRepository.buscarArtistaId( solic.id_artista)
                Log.d("viewmodelsolicitudesregistradas", "se llamo a la api para obtener datos extra: ${id_solicitud}, ${artista}")
                val obra: Obra = usuarioRepository.obtenerObra(solic.id_obra)
                Log.d("viewmodelsolicitudesregistradas", "se llamo a la api para obtener datos extra: ${id_solicitud}, ${artista}, ${obra}")
                val tecnica: Tecnica = usuarioRepository.obtenerTecnica(obra.id_tecnica)
                Log.d("viewmodelsolicitudesregistradas", "se llamo a la api para obtener datos extra: ${id_solicitud}, ${artista}, ${obra}, ${tecnica}")
                _uiState.update { currentState ->
                    currentState.copy(solicitudDatosArtista = SolicitudArtista( id_solicitud = id_solicitud, nombre_artista = artista.nombre, nombre=obra.nombre, fecha = obra.fecha, tecnica = tecnica.nombre_tecnica))
                }
                Log.d("viewmodelsolicitudesregistradas", "se llamo a la api para obtener datos extra: ${_uiState.value.solicitudDatosArtista}")
            } catch (e: Exception) {
                Log.d("viewmodelsolicitudesregistradas", "entro y agarro al catch")
                Log.d("viewmodelsolicitudesregistradas", e.message.toString())
            } finally {
                _uiState.update { currentState -> currentState.copy(isLoading = false) }
            }
        }
    }
    fun cargar() { _uiState.update {  currentState -> currentState.copy(isLoading = false) } }
}