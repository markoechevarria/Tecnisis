package com.markoen.tecnisisapp.ui.views.anfitrion.confirmarSolicitud

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Usuario
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmarSolicitudViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow( ConfirmarSolicitudUiState() )
    val uiState: StateFlow<ConfirmarSolicitudUiState> = _uiState.asStateFlow()

    fun obtenerDatosSolicitud(id: Int, id_perfil: Int, id_artista: Int, id_obra: Int, id_evaluador_artistico: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }
        viewModelScope.launch {
            try {
                val obra: Obra = usuarioRepository.obtenerObra( id = id_obra )
                val usuario: Usuario = usuarioRepository.obtenerUsuario( id = id_evaluador_artistico )
                val artista: Artista = usuarioRepository.buscarArtistaId( id = id_artista )
                _uiState.update { currentState -> currentState.copy( obra = obra, evaluadorArtisticoElegido = usuario, artista = artista, fotoObraUrl = obra.imagen_obra ) }
            } catch (e: Exception) {
                Log.d("viewmodelObtenerDatosSolicitud", "Aca es donde se obtienen los datos para registrar la solicitud: ${e.message}")
            } finally {
                _uiState.update { currentState -> currentState.copy( isLoading = false ) }
            }
        }
    }

    fun registrarSolicitud() {
        viewModelScope.launch {
            try {
                usuarioRepository.registrarSolicitud( _uiState.value.artista.id , _uiState.value.obra.id, _uiState.value.evaluadorArtisticoElegido.id, false, false, 0, 0 )
            } catch (e: Exception) {
                Log.d("viewmodelregistrarsolicitud", e.message.toString())
            }
        }
    }
}