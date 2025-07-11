package com.markoen.tecnisisapp.ui.views.anfitrion.solicitudesRegistradasAnfitrion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Solicitud
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolicitudesRegistradasViewModelAnfitrion @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SolicitudesRegistradasUiStateAnfitrion() )
    val uiState: StateFlow<SolicitudesRegistradasUiStateAnfitrion> = _uiState.asStateFlow()

    fun obtenerSolicitudes() {
        viewModelScope.launch {
            try {
                Log.d("viewmodelListarSolicitiudes", "entrando al try")
                val solicitudes: List<Solicitud> = usuarioRepository.obtenerSolicitudes()
                _uiState.update { currentState -> currentState.copy( listaSolicitudes = solicitudes ) }
                Log.d("viewmodelListarSolicitiudes", "se obtuvo: ${_uiState.value.listaSolicitudes}")

                if (_uiState.value.listaSolicitudes.isNotEmpty()) {
                    _uiState.value.listaSolicitudes.forEach { it ->
                        val datosArtista: Artista = usuarioRepository.buscarArtistaId(it.id_artista)
                        val datosObra: Obra = usuarioRepository.obtenerObra(it.id_obra)
                        _uiState.value.listaSolicitudesExtra.add(Pair(datosObra, datosArtista))
                        Log.d("viewmodel lista solictides", "se obtuvo: ${_uiState.value.listaSolicitudesExtra}")
                    }
                }
            } catch(e: Exception) {
                Log.d("viewmodelListarSolicitiudes", "entro y agarro al catch")
                Log.d("viewmodelListarSolicitiudes", e.message.toString())
            } finally {
                _uiState.update { currentState -> currentState.copy( isLoading = false ) }
            }
        }
    }
}