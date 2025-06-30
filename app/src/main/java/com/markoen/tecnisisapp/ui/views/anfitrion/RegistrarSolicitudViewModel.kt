package com.markoen.tecnisisapp.ui.views.anfitrion

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Obra
import com.markoen.tecnisisapp.domain.models.Tecnica
import com.markoen.tecnisisapp.domain.models.Usuario
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

import kotlinx.coroutines.tasks.await

@HiltViewModel
class RegistrarSolicitudViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrarSolicitudUIState() )
    val uiState: StateFlow<RegistrarSolicitudUIState> = _uiState.asStateFlow()

    private val storage = Firebase.storage
    private var _tempPhotoUri: Uri? = null


    fun imprimirId(id1: Int, id2: Int, id3:Int){
        Log.d("aca se ve lo q se recibe", "se recibio estos ids en order ${id1}, ${id2}, ${id3}")
    }
    fun imprimirId4(id1: Int, id2: Int, id3:Int, id4: Int){
        Log.d("aca se ve lo q se recibe", "se recibio estos ids en order ${id1}, ${id2}, ${id3}, ${id4}")
    }

    fun obtenerDatosSolicitud(id: Int, id_perfil: Int, id_artista: Int, id_obra: Int, id_evaluador_artistico: Int) {
        _uiState.update { currentState -> currentState.copy( id = id, id_perfil = id_perfil ) }
        obtenerArtista(id, id_perfil, id_artista)
        obtenerObra(id_obra)
        obtenerEvaluadorArtistico(id_evaluador_artistico)
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
    fun registrarObra() {
        viewModelScope.launch { _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                var finalPhotoUrl: String? = null
                _tempPhotoUri?.let { uri -> finalPhotoUrl = subirImagenFirebase(uri) }
                if (_uiState.value.photoUploadState is PhotoUploadState.Success || finalPhotoUrl == null) {
                    val result = usuarioRepository.registrarObra(
                        id_tecnica = _uiState.value.id_tecnica_obra,
                        id_artista = _uiState.value.artista.id,
                        imagen_obra = finalPhotoUrl ?: _uiState.value.fotoObraUrl,
                        nombre = _uiState.value.nombre_obra,
                        fecha = _uiState.value.fecha_obra,
                        dimensiones = _uiState.value.dimensiones_obra
                    )
                    if (result.id != 0) {
                        _uiState.update { it.copy(obra_registrada = true, id_obra = result.id) }
                    } else {
                        _uiState.update { it.copy(error = "Error al registrar la obra.") }
                    }
                } else { _uiState.update { it.copy(error = "No se pudo registrar la obra porque la foto no se subió correctamente.") } }
            } catch (e: Exception) { _uiState.update { it.copy(error = e.localizedMessage ?: "Error en el registro.") } }
            finally { _uiState.update { it.copy(isLoading = false) } }
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
    fun seleccionarExperto(id: Int) {
        _uiState.update { currentState ->
            currentState.copy( expertoSeleccionadoId = id, habilitadoBotonExpertoSeleccionado = true )
        }
    }

    fun obtenerTecnicas() {
        viewModelScope.launch {
            try {
                val tecnicas: List<Tecnica> = usuarioRepository.obtenerTecnicas()
                _uiState.update { currentState -> currentState.copy( tecnicasLista = tecnicas ) }
            } catch (e: Exception) {
                Log.d("viewmodelregistrarsolicitud", e.message.toString())
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
    
    fun actualizarFotoUriTemporal(uri: Uri?) { _tempPhotoUri = uri }
    private suspend fun subirImagenFirebase(imageUri: Uri): String {
        val storageRef = storage.reference
        val fileName = "obras_fotos/${System.currentTimeMillis()}_${imageUri.lastPathSegment ?: "image.jpg"}"
        val imageRef = storageRef.child(fileName)
        _uiState.update { it.copy(photoUploadState = PhotoUploadState.Loading) }
        return try {
            val uploadTask = imageRef.putFile(imageUri).await()
            val downloadUrl = imageRef.downloadUrl.await()
            val url = downloadUrl.toString()
            _uiState.update { it.copy(fotoObraUrl = url, photoUploadState = PhotoUploadState.Success(url))}
            url
        } catch (e: Exception) { _uiState.update { it.copy(photoUploadState = PhotoUploadState.Error(e.localizedMessage ?: "Error desconocido al subir foto")) }; throw e }
    }
}