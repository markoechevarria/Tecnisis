package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico

import android.util.Log
import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.data.ListaArtistas
import com.example.tecnisis.data.ListaObras
import com.example.tecnisis.data.ListaSolicitudesRegistradas
import com.example.tecnisis.data.ListaTecnicas
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Tecnica
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
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
                val solicitudes = usuarioRepository.obtenerSolicitudesEvaluadorArtistico(_uiState.value.id)
                _uiState.update { currentState -> currentState.copy( listaSolicitudes = solicitudes ) }

            } catch (e: Exception) {
                Log.d("viewmodelSolicitudesregsitradas", "entro y agarro al catch")
                Log.d("viewmodelSolicitudesregsitradas", e.message.toString())
            }
        }
    }

    fun obtenerDatosExtra() {
        _uiState.value.listaSolicitudes.forEach {
            viewModelScope.launch {
                try {
                    val artista: Artista = usuarioRepository.buscarArtistaId( it.id_artista)
                    val obra: Obra = usuarioRepository.obtenerObra(it.id_obra)
                    val tecnica: Tecnica = usuarioRepository.obtenerTecnica(obra.id_tecnica)
                    _uiState.value.solicitudesDatosArtista.add(SolicitudArtista(
                        id_solicitud = it.id, nombre = artista.nombre,fecha = obra.fecha, tecnica = tecnica.nombre_tecnica
                    ))
                } catch (e: Exception) {
                    Log.d("viewmodelsolicitudesregistradas", "entro y agarro al catch")
                    Log.d("viewmodel", e.message.toString())
                }
            }
        }
    }
}