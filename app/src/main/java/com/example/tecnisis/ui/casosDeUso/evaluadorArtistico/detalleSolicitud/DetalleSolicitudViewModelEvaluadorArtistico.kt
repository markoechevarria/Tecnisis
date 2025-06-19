package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.detalleSolicitud

import androidx.lifecycle.ViewModel
import com.example.tecnisis.data.ListaArtistas
import com.example.tecnisis.data.ListaEvaluadoresArtisticos
import com.example.tecnisis.data.ListaObras
import com.example.tecnisis.data.ListaSolicitudesRegistradas
import com.example.tecnisis.data.ListaTecnicas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetalleSolicitudViewModelEvaluadorArtistico:ViewModel() {
    private val _uiState = MutableStateFlow(DetalleSolicitudUiStateEvaluadorArtistico() )
    val uiState: StateFlow<DetalleSolicitudUiStateEvaluadorArtistico> = _uiState.asStateFlow()

    fun asignarIds(idSolicitud: Int, idUsuario: Int, idPerfil: Int) {
        _uiState.update { currentState -> currentState.copy(id_solicitud = idSolicitud, id_usuario = idUsuario, id_perfil = idPerfil ) }
    }
    fun obtenerDatosArtista(): artistaEvaluadorArtistico {
        val id_artista = ListaSolicitudesRegistradas.solicitudesRegistradas.find { solicitud -> solicitud.id == _uiState.value.id_solicitud }?.id_artista
        val artista = ListaArtistas.artistasRegistrados.find { artista -> artista.id == id_artista }
        return artistaEvaluadorArtistico(dni = artista!!.dni, nombre = artista.nombre, direccion = artista.direccion, telefono = artista.telefono)
    }

    fun obtenerDatosObra(): obraEvaluadorArtistico {
        val idObra = ListaSolicitudesRegistradas.solicitudesRegistradas.find { solicitud -> solicitud.id == _uiState.value.id_solicitud }?.id_obra
        val obra = ListaObras.obrasRegistrados.find { obraItem -> obraItem.id == idObra }
        val tecnica = ListaTecnicas.tecnicasRegistradas.find { tecnica -> tecnica.id == obra?.id_tecnica }
        val id_experto = ListaSolicitudesRegistradas.solicitudesRegistradas.find { solicitud -> solicitud.id == _uiState.value.id_solicitud }?.id_experto
        val experto = ListaEvaluadoresArtisticos.evaluadoresArtisticos.find { experto -> experto.id == id_experto }
        val estadoSolicitud = ListaSolicitudesRegistradas.solicitudesRegistradas.find { solicitud -> solicitud.id == _uiState.value.id_solicitud }
        return obraEvaluadorArtistico( tecnica = tecnica!!.nombre_tecnica , fecha=obra!!.fecha, dimensiones=obra.dimensiones, experto = experto!!.nombre, estadoSolicitud = estadoSolicitud!!.aprobadaEvaluadorArtistico)
    }

    fun asignarDatos() {
        val artistatemporal = obtenerDatosArtista()
        val obratemporal = obtenerDatosObra()
        _uiState.update { currentState -> currentState.copy(
            dni = artistatemporal.dni,
            nombre = artistatemporal.nombre,
            direccion = artistatemporal.direccion,
            telefono = artistatemporal.telefono,
            tecnica = obratemporal.tecnica,
            fecha = obratemporal.fecha,
            dimensiones = obratemporal.dimensiones,
            nombreExperto = obratemporal.experto,
            estadoSolicitudEconomico = obratemporal.estadoSolicitud
        ) }
    }
}