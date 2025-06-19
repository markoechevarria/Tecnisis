package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.evaluacionEconomica

import android.R
import androidx.lifecycle.ViewModel
import com.example.tecnisis.data.ListaEvaluadoresArtisticos
import com.example.tecnisis.data.ListaObras
import com.example.tecnisis.data.ListaSolicitudesRegistradas
import com.example.tecnisis.data.ListaTecnicas
import com.example.tecnisis.data.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EvaluacionEconomicaViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(EvaluacionEconomicaUiState() )
    val uiState: StateFlow<EvaluacionEconomicaUiState> = _uiState.asStateFlow()

    fun asignarIds(idSolicitud: Int, idUsuario: Int, idPerfil: Int) {
        _uiState.update { currentState -> currentState.copy(id_solicitud = idSolicitud, id_usuario = idUsuario, id_perfil = idPerfil ) }
    }
    fun obtenerDatosExperto(): expertoEvaluadorEconomico {
        val id_experto = ListaSolicitudesRegistradas.solicitudesRegistradas.find { solicitud -> solicitud.id == _uiState.value.id_solicitud }?.id_experto
        val experto = ListaEvaluadoresArtisticos.evaluadoresArtisticos.find { expert -> expert.id == id_experto } ?: Usuario(0,0,"","","")
        return expertoEvaluadorEconomico(id_experto = experto!!.id, nombre_experto = experto.nombre)
    }

    fun obtenerDatosObra(): obraEvaluadorEconomico {
        val idObra = ListaSolicitudesRegistradas.solicitudesRegistradas.find { solicitud -> solicitud.id == _uiState.value.id_solicitud }?.id_obra
        val obra = ListaObras.obrasRegistrados.find { obraItem -> obraItem.id == idObra }
        val tecnica = ListaTecnicas.tecnicasRegistradas.find { tecnica -> tecnica.id == obra?.id_tecnica }
        val estadoSolicitud = ListaSolicitudesRegistradas.solicitudesRegistradas.find { solicitud -> solicitud.id == _uiState.value.id_solicitud }
        return obraEvaluadorEconomico( id_obra = obra!!.id, tecnica = tecnica!!.nombre_tecnica , fecha = obra!!.fecha , dimensiones = obra.dimensiones ,aprobadaEvaluadorArtistico = estadoSolicitud!!.aprobadaEvaluadorArtistico , aprobadaEvaluadorEconomico = estadoSolicitud.aprobadaEValuadorEconomico )
    }
    fun asignarVenta( venta: Double ) { _uiState.update {  currentState -> currentState.copy( precioVenta = venta ?: 0.0) } }
    fun asignarPorcentajeVenta( porcentaje: Double ) { _uiState.update {  currentState -> currentState.copy( porcentajeGanancia = porcentaje ?: 0.0 ) } }

    fun asignarDatos() {
        val expertoTemporal = obtenerDatosExperto()
        val datosObraTemporal = obtenerDatosObra()
        _uiState.update { currentState -> currentState.copy(
            id_obra = datosObraTemporal.id_obra,
            tecnica = datosObraTemporal.tecnica,
            fecha = datosObraTemporal.fecha,
            dimensiones = datosObraTemporal.dimensiones,
            id_experto = expertoTemporal.id_experto,
            nombre_experto = expertoTemporal.nombre_experto,
            aprobadaEvaluadorArtistico = datosObraTemporal.aprobadaEvaluadorArtistico,
            aprobadaEvaluadorEconomico = datosObraTemporal.aprobadaEvaluadorEconomico,
        ) }
    }
    fun cambiarVentanaAprobado() {
        var valor: Boolean = _uiState.value.showDialogAprobado
        _uiState.update { currentState -> currentState.copy( showDialogAprobado = !valor ) }
    }

    fun asignarAprobacion( aprobado: Boolean ) {
        _uiState.update { currentState -> currentState.copy( aprobado = aprobado ) }
    }
}