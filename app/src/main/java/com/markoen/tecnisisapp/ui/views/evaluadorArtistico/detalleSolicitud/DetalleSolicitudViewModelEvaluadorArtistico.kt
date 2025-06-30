package com.markoen.tecnisisapp.ui.views.evaluadorArtistico.detalleSolicitud

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoen.tecnisisapp.domain.models.Usuario
import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Solicitud
import com.markoen.tecnisisapp.domain.models.Tecnica
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleSolicitudViewModelEvaluadorArtistico @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
):ViewModel() {
    private val _uiState = MutableStateFlow(DetalleSolicitudUiStateEvaluadorArtistico() )
    val uiState: StateFlow<DetalleSolicitudUiStateEvaluadorArtistico> = _uiState.asStateFlow()

    fun asignarIds(idSolicitud: Int, idUsuario: Int, idPerfil: Int) {
        _uiState.update { currentState -> currentState.copy(id_solicitud = idSolicitud, id_usuario = idUsuario, id_perfil = idPerfil ) }
        obtenerSolicitud(idSolicitud)
        obtenerDatosExtra(_uiState.value.solicitudObjeto)
    }

    fun obtenerSolicitud(id: Int) {
        viewModelScope.launch {
            try {
                val solicitud = usuarioRepository.obtenerSolicitudPorId(id)
                _uiState.update { currentState -> currentState.copy( solicitudObjeto = solicitud ) }
                Log.d("viewmodelobtenersolicitudesdetalles", "se llamo a la api para obtener datos extra y se obtuvo esta solicitud de id: ${id}")
            } catch (e: Exception) {
                Log.d("viewmodelobtenersolicitudesdetalles", "entro y agarro al catch")
                Log.d("viewmodelobtenersolicitudesdetalles", e.message.toString())
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

                val evaluador_artistico: Usuario = usuarioRepository.obtenerUsuario(solic.id_evaluador_artistico)
                Log.d("viewmodelsolicitudesregistradas", "se llamo a la api para obtener datos extra: ${id_solicitud}, ${artista}, ${obra}, ${tecnica}")
                _uiState.update { currentState ->
                    currentState.copy(
                        dni = artista.dni,
                        nombre = artista.nombre,
                        direccion = artista.direccion,
                        telefono = artista.telefono,
                        obraurl = obra.imagen_obra,
                        tecnica = tecnica.nombre_tecnica,
                        fecha = obra.fecha,
                        dimensiones = obra.dimensiones,
                        nombreExperto = evaluador_artistico.nombre,
                        estadoSolicitudArtistico = solic.aprobadaEvaluadorArtistico,
                        estadoSolicitudEconomico = solic.aprobadaEValuadorEconomico
                    )
                }
                Log.d("viewmodelsolicitudesregistradas", "se llamo a la api para obtener datos extra y se obtuvieron")
            } catch (e: Exception) {
                Log.d("viewmodelsolicitudesregistradas", "entro y agarro al catch")
                Log.d("viewmodelsolicitudesregistradas", e.message.toString())
            }
        }
    }
}