package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesAprobadasEvaluadorArtistico

import androidx.lifecycle.ViewModel
import com.example.tecnisis.data.ListaArtistas
import com.example.tecnisis.data.ListaObras
import com.example.tecnisis.data.ListaSolicitudesRegistradas
import com.example.tecnisis.data.ListaTecnicas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SolicitudesAprobadasEvaluadorArtisticoViewModel :ViewModel() {
    private val _uiState = MutableStateFlow(SolicitudesAprobadasEvaluadorArtisticoUiState() )
    val uiState: StateFlow<SolicitudesAprobadasEvaluadorArtisticoUiState> = _uiState.asStateFlow()

    fun actualizarDatos( id: Int, idPerfil: Int ) {
        _uiState.update { currentState -> currentState.copy( id = id, idPerfil = idPerfil ) }

        val solicitudes: List<SolicitudArtistaAprobado>  = ListaSolicitudesRegistradas.solicitudesRegistradas.map {
            it -> SolicitudArtistaAprobado( it.id , it.id_artista, it.id_obra, it.id_experto, it.aprobadaEvaluadorArtistico )
        }
        val solicitudesAprobadas: List< SolicitudArtistaAprobado> = solicitudes.filter {
            solicitud -> solicitud.aprobadaEvaluadorArtistico == true
        }
        _uiState.update { currentState -> currentState.copy( listaSolicitudesAprobadas = solicitudesAprobadas ) }
    }
    fun obtenerNombreFechaTecnicaAprobado (idArtista: Int, idObra: Int): nombreFechaTecnicaAprobado {
        val nombre: String = ListaArtistas.artistasRegistrados.find { it.id == idArtista }?.nombre ?: ""
        val fecha: String = ListaObras.obrasRegistrados.find { it.id == idObra  }?.fecha ?: ""
        val id_tecnica: Int = ListaObras.obrasRegistrados.find {it.id == idObra}?.id_tecnica?: 0
        val tecnica: String = ListaTecnicas.tecnicasRegistradas.find { it.id == id_tecnica }?.nombre_tecnica ?: ""
        return nombreFechaTecnicaAprobado(nombre,fecha,tecnica)
    }

    fun obtenerDatosSolicitudes() {
        val nuevaSolicitudes: List<SolicitudArtistaAprobado> = _uiState.value.listaSolicitudesAprobadas.map { solicitud ->
            solicitud.copy(
                nombre =  obtenerNombreFechaTecnicaAprobado(solicitud.id_artista, solicitud.id_obra).nombre,
                fecha =  obtenerNombreFechaTecnicaAprobado(solicitud.id_artista, solicitud.id_obra).fecha,
                tecnica =  obtenerNombreFechaTecnicaAprobado(solicitud.id_artista, solicitud.id_obra).tecnica,
            )
        }
        _uiState.update { currentState -> currentState.copy( listaSolicitudesAprobadas = nuevaSolicitudes ) }
    }
}