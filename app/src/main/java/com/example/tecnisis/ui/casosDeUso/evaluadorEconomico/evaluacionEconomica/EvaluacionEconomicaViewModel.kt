package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.evaluacionEconomica

import android.R
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.data.ListaEvaluadoresArtisticos
import com.example.tecnisis.data.ListaObras
import com.example.tecnisis.data.ListaSolicitudesRegistradas
import com.example.tecnisis.data.ListaTecnicas
import com.example.tecnisis.domain.models.Usuario
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Solicitud
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
class EvaluacionEconomicaViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(EvaluacionEconomicaUiState() )
    val uiState: StateFlow<EvaluacionEconomicaUiState> = _uiState.asStateFlow()

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
                _uiState.update { currentState ->
                    currentState.copy(
                        id_obra = obra.id,
                        nombre_obra = obra.nombre,
                        tecnica = tecnica.nombre_tecnica,
                        fecha = obra.fecha,
                        dimensiones = obra.dimensiones,
                        id_experto = evaluador_artistico.id,
                        nombre_experto = evaluador_artistico.nombre,
                        aprobadaEvaluadorArtistico = solicitud.aprobadaEvaluadorArtistico,
                        aprobadaEvaluadorEconomico = solicitud.aprobadaEValuadorEconomico,
                    )
                }
                Log.d("viewmodelSolicitudesEvaluarEconomico", "se llamo a la api para obtener datos extra")
            } catch (e: Exception) {
                Log.d("viewmodelSolicitudesEvaluarEconomico", "entro y agarro al catch")
                Log.d("viewmodelSolicitudesEvaluarEconomico", e.message.toString())
            }
        }
    }

    fun asignarVenta( venta: Int ) { _uiState.update {  currentState -> currentState.copy( precioVenta = venta ) } }
    fun asignarPorcentajeVenta( porcentaje: Int ) { _uiState.update {  currentState -> currentState.copy( porcentajeGanancia = porcentaje ) } }

    fun cambiarVentanaAprobado() {
        var valor: Boolean = _uiState.value.showDialogAprobado
        _uiState.update { currentState -> currentState.copy( showDialogAprobado = !valor ) }

    }

    fun asignarAprobacion( aprobado: Boolean ) {
        _uiState.update { currentState -> currentState.copy( aprobado = aprobado ) }
        viewModelScope.launch {
            try {
                val solicitudAsignada: Solicitud = usuarioRepository.asignarPrecios( _uiState.value.id_solicitud, _uiState.value.precioVenta, _uiState.value.porcentajeGanancia)
            } catch (e: Exception) {
                Log.d("viewmodelSolicitudesEvaluarEconomico", "entro y agarro al catch")
                Log.d("viewmodelSolicitudesEvaluarEconomico", e.message.toString())
            }
        }
    }
}