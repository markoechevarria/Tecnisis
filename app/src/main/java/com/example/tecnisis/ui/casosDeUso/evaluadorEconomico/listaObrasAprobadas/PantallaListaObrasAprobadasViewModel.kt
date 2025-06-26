package com.example.tecnisis.ui.casosDeUso.evaluadorEconomico.listaObrasAprobadas

import androidx.lifecycle.ViewModel
import com.example.tecnisis.data.ListaArtistas
import com.example.tecnisis.data.ListaObras
import com.example.tecnisis.data.ListaSolicitudesRegistradas
import com.example.tecnisis.data.ListaTecnicas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PantallaListaObrasAprobadasViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PantallaListaObrasAprobadasUiState() )
    val uiState: StateFlow<PantallaListaObrasAprobadasUiState> = _uiState.asStateFlow()

    fun actualizarDatos( id: Int, idPerfil: Int ) {
        _uiState.update { currentState -> currentState.copy( id = id, idPerfil = idPerfil ) }

        val solicitudes: List<obraAprobadaEconomico>  = ListaSolicitudesRegistradas.solicitudesRegistradas.map {
                it -> obraAprobadaEconomico( it.id , it.id_artista, it.id_obra, it.id_experto, it.aprobadaEvaluadorArtistico, it.aprobadaEValuadorEconomico )
        }
        val solicitudesFiltradasPorEvaluadorEconomico: List<obraAprobadaEconomico> = solicitudes.filter { it -> it.aprobadaEvaluadorEconomico }
        _uiState.update { currentState -> currentState.copy( listaObrasAprobadas = solicitudesFiltradasPorEvaluadorEconomico ) }
    }
    fun obtenerNombreFechaTecnica (idArtista: Int, idObra: Int, idSolicitud: Int): nombreFechaTecnicaEconomicoAprobadoEconomico{
        val nombre: String = ListaArtistas.artistasRegistrados.find { it.id == idArtista }?.nombre ?: ""
        val fecha: String = ListaObras.obrasRegistrados.find { it.id == idObra  }?.fecha ?: ""
        val id_tecnica: Int = ListaObras.obrasRegistrados.find {it.id == idObra}?.id_tecnica?: 0
        val tecnica: String = ListaTecnicas.tecnicasRegistradas.find { it.id == id_tecnica }?.nombre_tecnica ?: ""
        val aprobadaEvaluadorArtistico: Boolean = ListaSolicitudesRegistradas.solicitudesRegistradas.find { it.id == idSolicitud }?.aprobadaEvaluadorArtistico ?: false
        val aprobadaEvaluadorEconomico: Boolean = ListaSolicitudesRegistradas.solicitudesRegistradas.find { it.id == idSolicitud }?.aprobadaEValuadorEconomico ?: false
        return nombreFechaTecnicaEconomicoAprobadoEconomico(nombre,fecha,tecnica, aprobadaEvaluadorArtistico, aprobadaEvaluadorEconomico)
    }
    fun obtenerDatosSolicitudes() {
        val nuevaSolicitudes: List<obraAprobadaEconomico> = _uiState.value.listaObrasAprobadas.map { solicitud ->
            solicitud.copy(
                nombre =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).nombre,
                fecha =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).fecha,
                tecnica =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).tecnica,
                aprobadaEvaluadorArtistico =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).aprobadaEvaluadorArtistico,
                aprobadaEvaluadorEconomico =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra, solicitud.id_solicitud).aprobadaEvaluadorEconomico,
            )
        }
        val SolicitudesRevisadas: List<obraAprobadaEconomico> = nuevaSolicitudes.filter { it.aprobadaEvaluadorEconomico }
        _uiState.update { currentState -> currentState.copy( listaObrasAprobadas = SolicitudesRevisadas ) }
    }
}