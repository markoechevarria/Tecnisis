package com.example.tecnisis.ui.views.anfitrion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.domain.models.Artista
import com.example.tecnisis.domain.models.Obra
import com.example.tecnisis.domain.models.Usuario
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrarSolicitudViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrarSolicitudUIState() )
    val uiState: StateFlow<RegistrarSolicitudUIState> = _uiState.asStateFlow()

    fun obtenerDatosSolicitud(id: Int, id_perfil: Int, id_artista: Int, id_obra: Int, id_evaluador_artistico: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }
        obtenerArtista(id, id_perfil, id_artista)
        obtenerObra(id_obra)
        obtenerEvaluadorArtistico(id_evaluador_artistico)
    }

    fun imprimirId(id1: Int, id2: Int, id3:Int){
        Log.d("aca se ve lo q se recibe", "se recibio estos ids en order ${id1}, ${id2}, ${id3}")
    }
    fun imprimirId4(id1: Int, id2: Int, id3:Int, id4: Int){
        Log.d("aca se ve lo q se recibe", "se recibio estos ids en order ${id1}, ${id2}, ${id3}, ${id4}")
    }

    fun obtenerObra(id_obra: Int) {
        viewModelScope.launch {
            try {
                Log.d("viewmodelobtenerobra", "aca es donde se obtiene la obra")
                val obra: Obra = usuarioRepository.obtenerObra( id = id_obra )
                _uiState.update { currentState -> currentState.copy( obra = obra ) }
                Log.d("viewmodelobtenerartista", "ya se obtuvo el usuario de id ${_uiState.value.obra}")
            }catch (e: Exception) {
                Log.d("viewmodelobtenerobra", "entro y agarro al catch")
                Log.d("viewmodelobtenerobra", e.message.toString())
            }
        }
    }

    fun obtenerEvaluadorArtistico(id_evaluador_artistico: Int) {
        viewModelScope.launch {
            try {
                Log.d("viewmodelobtenerevaluardorartistico", "aca es donde se obtiene el evaluador Artistico")
                val usuario: Usuario = usuarioRepository.obtenerUsuario( id = id_evaluador_artistico )
                _uiState.update { currentState -> currentState.copy( evaluadorArtisticoElegido = usuario ) }
            }catch (e: Exception) {
                Log.d("viewmodelobtenerevaluardorartistico", "entro y agarro al catch")
                Log.d("viewmodelobtenerevaluardorartistico", e.message.toString())
            }
        }
    }

    fun obtenerArtista( id: Int, id_perfil: Int, id_artista: Int ) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil, )}
        viewModelScope.launch {
            try {
                Log.d("viewmodelobtenerartista", "aca es donde se obtiene el usuario")
                val artista: Artista = usuarioRepository.buscarArtistaId( id = id_artista )
                _uiState.update { currentState -> currentState.copy( artista = artista ) }
                Log.d("viewmodelobtenerartista", "ya se obtuvo el usuario de id ${_uiState.value.artista}")
            } catch (e: Exception) {
                Log.d("viewmodelobtenerartista", "entro y agarro al catch")
                Log.d("viewmodelobtenerartista", e.message.toString())
            }
        }
    }
    fun listarEvaluadoresArtisticos() {
        viewModelScope.launch {
            try {
                Log.d("viewmodelregistrarsoli", "aca es donde se obtiene lso evaluadores artisticos")
                val evaluadoresArtisticos = usuarioRepository.listarEvaluadoresArtisticos()
                _uiState.update { currentState ->
                    currentState.copy(listaEvaluadoresArtisticos = evaluadoresArtisticos )
                }
            } catch (e: Exception) {
                Log.d("viewmodelregistrarsoli", "entro y agarro al catch")
                Log.d("viewmodel", e.message.toString())
            }
        }
    }
    fun registrarObra() {
        viewModelScope.launch {
            try {
                Log.d("viewmodelregistrarobra", "estos son los datos a entrear: ${_uiState.value.id_tecnica_obra} y ${_uiState.value.artista.id}")
                Log.d("viewmodelregistrarobra", "estos son los datos de la obra: ${_uiState.value.id_tecnica_obra}, ${_uiState.value.artista.id}, ${_uiState.value.nombre_obra} ,${_uiState.value.fecha_obra}, ${_uiState.value.dimensiones_obra}")
                Log.d("viewmodelregistrarobra", "aca es donde se registra la obra")
                var obraRegistrada = usuarioRepository.registrarObra(_uiState.value.id_tecnica_obra, _uiState.value.artista.id, _uiState.value.nombre_obra ,_uiState.value.fecha_obra, _uiState.value.dimensiones_obra)
                _uiState.update { currentState -> currentState.copy( obra = obraRegistrada, id_obra = obraRegistrada.id, obra_registrada = true ) }
            } catch (e: Exception) {
                Log.d("viewmodelregistrarobra", "entro y agarro al catch")
                Log.d("viewmodelregistrarobra", e.message.toString())
            }
        }
    }

    fun registrarSolicitud() {
        viewModelScope.launch {
            try {
                Log.d("viewmodelregistrarsolicitud", "aca es donde se registra una solicitud")
                var solicitudRegistrada = usuarioRepository.registrarSolicitud(
                    _uiState.value.artista.id ,
                    _uiState.value.obra.id,
                    _uiState.value.evaluadorArtisticoElegido.id,
                    false,
                    false,
                    0, 0
                )
            } catch (e: Exception) {
                Log.d("viewmodelregistrarsolicitud", "entro y agarro al catch")
                Log.d("viewmodelregistrarsolicitud", e.message.toString())
            }
        }
    }

    fun actualizarOpciones() {
        if (_uiState.value.seEncontro) {
            _uiState.update { currentState -> currentState.copy(habilitadoBotonObra = true) }
            _uiState.update { currentState -> currentState.copy( habilitadoBotonArtista = false) }
        } else {
            _uiState.update { currentState -> currentState.copy(habilitadoBotonObra = false) }
            _uiState.update { currentState -> currentState.copy( habilitadoBotonArtista = true) }
        }
    }

    // ventana de obra

    fun actualizarNombreObra( nombre: String) {
        _uiState.update { currentState -> currentState.copy( nombre_obra = nombre) }
        habilitarBotonObra()
    }
    fun actualizarFechaObra( fecha: String) {
        _uiState.update { currentState -> currentState.copy( fecha_obra = fecha) }
        habilitarBotonObra()
    }
    fun actualizarTecnicaObra( id_tecnica_obra: Int) {
        _uiState.update { currentState -> currentState.copy( id_tecnica_obra = id_tecnica_obra) }
        habilitarBotonObra()
    }
    fun actualizarDimensionesObra( dimensiones: String) {
        _uiState.update { currentState -> currentState.copy( dimensiones_obra = dimensiones) }
        habilitarBotonObra()
    }
    fun habilitarBotonObra() {
        if ( _uiState.value.nombre_obra != "" && _uiState.value.fecha_obra != "" && _uiState.value.dimensiones_obra != "" && _uiState.value.id_tecnica_obra != 0 ) {
            _uiState.update { currentState -> currentState.copy( habilitadoBotonObra = true ) }
        }
    }

    fun seleccionarExperto(id: Int) {
        _uiState.update { currentState ->
            currentState.copy( expertoSeleccionadoId = id, habilitadoBotonExpertoSeleccionado = true )
        }
    }
}