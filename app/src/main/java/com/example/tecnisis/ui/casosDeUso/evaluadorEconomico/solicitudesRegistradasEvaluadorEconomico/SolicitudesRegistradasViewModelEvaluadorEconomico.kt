package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.solicitudesRegistradasEvaluadorEconomico

import androidx.lifecycle.ViewModel
import com.example.tecnisis.data.ListaArtistas
import com.example.tecnisis.data.ListaObras
import com.example.tecnisis.data.ListaSolicitudesRegistradas
import com.example.tecnisis.data.ListaTecnicas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SolicitudesRegistradasViewModelEvaluadorEconomico : ViewModel() {
    private val _uiState = MutableStateFlow(SolicitudesRegistradasUiStateEvaluadorEconomico() )
    val uiState: StateFlow<SolicitudesRegistradasUiStateEvaluadorEconomico> = _uiState.asStateFlow()

    fun actualizarDatos( id: Int, idPerfil: Int ) {
        _uiState.update { currentState -> currentState.copy( id = id, idPerfil = idPerfil ) }

        val solicitudes: List<SolicitudArtistaEconomico>  = ListaSolicitudesRegistradas.solicitudesRegistradas.map {
                it -> SolicitudArtistaEconomico( it.id , it.id_artista, it.id_obra, it.id_experto, it.aprobadaEvaluadorArtistico,it.aprobadaEValuadorEconomico )
        }
        _uiState.update { currentState -> currentState.copy( listaSolicitudes = solicitudes ) }
    }
    fun obtenerNombreFechaTecnica (idArtista: Int, idObra: Int, idSolicitud: Int): nombreFechaTecnicaEconomico {
        val nombre: String = ListaArtistas.artistasRegistrados.find { it.id == idArtista }?.nombre ?: ""
        val fecha: String = ListaObras.obrasRegistrados.find { it.id == idObra  }?.fecha ?: ""
        val id_tecnica: Int = ListaObras.obrasRegistrados.find {it.id == idObra}?.id_tecnica?: 0
        val tecnica: String = ListaTecnicas.tecnicasRegistradas.find { it.id == id_tecnica }?.nombre_tecnica ?: ""
        val aprobadaEvaluadorArtistico: Boolean = ListaSolicitudesRegistradas.solicitudesRegistradas.find { it.id == idSolicitud }?.aprobadaEvaluadorArtistico ?: false
        val aprobadaEvaluadorEconomico: Boolean = ListaSolicitudesRegistradas.solicitudesRegistradas.find { it.id == idSolicitud }?.aprobadaEValuadorEconomico ?: false
        return nombreFechaTecnicaEconomico(nombre,fecha,tecnica, aprobadaEvaluadorArtistico, aprobadaEvaluadorEconomico)
    }
    fun obtenerDatosSolicitudes() {
        val nuevaSolicitudes: List<SolicitudArtistaEconomico> = _uiState.value.listaSolicitudes.map { solicitud ->
            solicitud.copy(
                nombre =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).nombre,
                fecha =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).fecha,
                tecnica =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).tecnica,
                aprobadaEvaluadorArtistico =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).aprobadaEvaluadorArtistico,
                aprobadaEvaluadorEconomico =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).aprobadaEvaluadorEconomico,
            )
        }
        val SolicitudesRevisadas: List<SolicitudArtistaEconomico> = nuevaSolicitudes.filter { it.aprobadaEvaluadorArtistico }
        _uiState.update { currentState -> currentState.copy( listaSolicitudes = SolicitudesRevisadas ) }
    }
}