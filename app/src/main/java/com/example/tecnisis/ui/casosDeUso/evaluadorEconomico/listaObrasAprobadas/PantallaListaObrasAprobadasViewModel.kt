package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.listaObrasAprobadas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.data.ListaArtistas
import com.example.tecnisis.data.ListaObras
import com.example.tecnisis.data.ListaSolicitudesRegistradas
import com.example.tecnisis.data.ListaTecnicas
import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Solicitud
import com.example.tecnisis.domain.models.Tecnica
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesAprobadasEvaluadorArtistico.SolicitudArtistaAprobada
import com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesAprobadasEvaluadorArtistico.SolicitudesAprobadasEvaluadorArtisticoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaListaObrasAprobadasViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(PantallaListaObrasAprobadasUiState() )
    val uiState: StateFlow<PantallaListaObrasAprobadasUiState> = _uiState.asStateFlow()

    fun actualizarDatos( id: Int, idPerfil: Int ) {
        _uiState.update { currentState -> currentState.copy( id = id, idPerfil = idPerfil ) }

        viewModelScope.launch {
            try {
                val solicitudes = usuarioRepository.obtenerSolicitudes().filter { it.aprobadaEValuadorEconomico }
                _uiState.update { currentState -> currentState.copy( listaSolicitudes = solicitudes ) }
            } catch (e: Exception) {
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", "entro y agarro al catch")
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", e.message.toString())
            }
        }
    }

    fun obtenerDatosExtra( solic: Solicitud ) {
        viewModelScope.launch {
            try {
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", "se llamo a la api para obtener datos extra: ${solic}")
                val id_solicitud: Int = solic.id
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", "se llamo a la api para obtener datos extra: ${id_solicitud}")
                val artista: Artista = usuarioRepository.buscarArtistaId( solic.id_artista)
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", "se llamo a la api para obtener datos extra: ${id_solicitud}, ${artista}")
                val obra: Obra = usuarioRepository.obtenerObra(solic.id_obra)
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", "se llamo a la api para obtener datos extra: ${id_solicitud}, ${artista}, ${obra}")
                val tecnica: Tecnica = usuarioRepository.obtenerTecnica(obra.id_tecnica)
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", "se llamo a la api para obtener datos extra: ${id_solicitud}, ${artista}, ${obra}, ${tecnica}")
                _uiState.update { currentState ->
                    currentState.copy(solicitudDatosArtista = SolicitudArtistaAprobadaPorEconomico( id_solicitud = id_solicitud, nombre_artista = artista.nombre, nombre=obra.nombre, fecha = obra.fecha, tecnica = tecnica.nombre_tecnica))
                }
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", "se llamo a la api para obtener datos extra: ${_uiState.value.solicitudDatosArtista}")
            } catch (e: Exception) {
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", "entro y agarro al catch")
                Log.d("viewmodelSolicitudesaprobadasRevisarEconomico", e.message.toString())
            }
        }
    }
}