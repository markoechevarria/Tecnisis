package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.detalleSolicitudAprobadaEconomico

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Solicitud
import com.example.tecnisis.domain.models.Tecnica
import com.example.tecnisis.domain.models.Usuario
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.listaObrasAprobadas.carta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleSolicitudViewModelAprobadoEconomico @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(DetalleSolicitudUiStateAprobadoEconomico() )
    val uiState: StateFlow<DetalleSolicitudUiStateAprobadoEconomico> = _uiState.asStateFlow()

    fun asignarIds( id: Int, idPerfil: Int, idSolicitud: Int) {
        _uiState.update { currentState -> currentState.copy( id_usuario = id, id_perfil = idPerfil, id_solicitud = idSolicitud ) }
        obtenerDatosExtra(_uiState.value.id_solicitud)
    }

    fun obtenerDatosExtra( id_solicitud: Int ) {
        viewModelScope.launch {
            try {
                Log.d("viewmodelSolicitudesRegistradasRevisarEconomico", "se llamo a la api para obtener datos extra, id de la solicitud: ${id_solicitud}")
                val solicitud: Solicitud = usuarioRepository.obtenerSolicitudPorId(id_solicitud)
                Log.d("viewmodelSolicitudesRegistradasRevisarEconomico", "se llamo a la api para obtener datos extra: ${id_solicitud}")
                val evaluador_artistico: Usuario = usuarioRepository.obtenerUsuario( solicitud.id_evaluador_artistico)
                Log.d("viewmodelSolicitudesRegistradasRevisarEconomico", "se llamo a la api para obtener datos extra: ${id_solicitud}")
                val obra: Obra = usuarioRepository.obtenerObra(solicitud.id_obra)
                Log.d("viewmodelSolicitudesRegistradasRevisarEconomico", "se llamo a la api para obtener datos extra: ${id_solicitud}, ${obra}")
                val tecnica: Tecnica = usuarioRepository.obtenerTecnica(obra.id_tecnica)
                Log.d("viewmodelSolicitudesRegistradasRevisarEconomico", "se llamo a la api para obtener datos extra: ${id_solicitud}, ${obra}, ${tecnica}")
                val artista: Artista = usuarioRepository.buscarArtistaId(solicitud.id_artista)
                _uiState.update { currentState ->
                    currentState.copy(
                        dni_artista = artista.dni,
                        nombre_artista = artista.nombre,
                        direccion = artista.direccion,
                        telefono = artista.telefono,

                        nombre_obra = obra.nombre,
                        tecnica = tecnica.nombre_tecnica,
                        fecha = obra.fecha,
                        dimensiones = obra.dimensiones,

                        correo_experto = evaluador_artistico.correo,
                        nombreExperto = evaluador_artistico.nombre
                    )
                }
                Log.d("viewmodelSolicitudesEvaluarEconomico", "se llamo a la api para obtener datos extra")
            } catch (e: Exception) {
                Log.d("viewmodelSolicitudesEvaluarEconomico", "entro y agarro al catch")
                Log.d("viewmodelSolicitudesEvaluarEconomico", e.message.toString())
            }
        }
    }
}