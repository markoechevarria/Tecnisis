package com.example.tecnisis.ui.casosDeUso.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico

import androidx.lifecycle.ViewModel
import com.example.tecnisis.data.ListaArtistas
import com.example.tecnisis.data.ListaObras
import com.example.tecnisis.data.ListaSolicitudesRegistradas
import com.example.tecnisis.data.ListaTecnicas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SolicitudesRegistradasViewModelEvaluadorArtistico: ViewModel() {
    private val _uiState = MutableStateFlow(SolicitudesRegistradasUiStateEvaluadorArtistico() )
    val uiState: StateFlow<SolicitudesRegistradasUiStateEvaluadorArtistico> = _uiState.asStateFlow()

    fun actualizarDatos( id: Int, idPerfil: Int ) {
        _uiState.update { currentState -> currentState.copy( id = id, idPerfil = idPerfil ) }

        val solicitudes: List<SolicitudArtista>  = ListaSolicitudesRegistradas.solicitudesRegistradas.map {
            it -> SolicitudArtista( it.id , it.id_artista, it.id_obra, it.id_experto, it.aprobadaEvaluadorArtistico )
        }
        _uiState.update { currentState -> currentState.copy( listaSolicitudes = solicitudes ) }
    }
    fun obtenerNombreFechaTecnica (idArtista: Int, idObra: Int): nombreFechaTecnica {
        val nombre: String = ListaArtistas.artistasRegistrados.find { it.id == idArtista }?.nombre ?: ""
        val fecha: String = ListaObras.obrasRegistrados.find { it.id == idObra  }?.fecha ?: ""
        val id_tecnica: Int = ListaObras.obrasRegistrados.find {it.id == idObra}?.id_tecnica?: 0
        val tecnica: String = ListaTecnicas.tecnicasRegistradas.find { it.id == id_tecnica }?.nombre_tecnica ?: ""
        return nombreFechaTecnica(nombre,fecha,tecnica)
    }

    fun obtenerDatosSolicitudes() {
        val nuevaSolicitudes: List<SolicitudArtista> = _uiState.value.listaSolicitudes.map { solicitud ->
            solicitud.copy(
                nombre =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra).nombre,
                fecha =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra).fecha,
                tecnica =  obtenerNombreFechaTecnica(solicitud.id_artista, solicitud.id_obra).tecnica,
            )
        }
        _uiState.update { currentState -> currentState.copy( listaSolicitudes = nuevaSolicitudes ) }
    }
}