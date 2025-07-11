package com.markoen.tecnisisapp.ui.views.anfitrion.registrarObra

import android.net.Uri
import com.markoen.tecnisisapp.domain.models.Artista
import com.markoen.tecnisisapp.domain.models.Tecnica

data class RegistrarObraUIState(
    val habilitadoBotonObra: Boolean= false,

    val id: Int = 0,
    val id_perfil: Int = 0,
    val artista: Artista = Artista(id=0, nombre="",dni="", direccion="", telefono="", ),

    val obra_registrada: Boolean = false,
    val id_obra: Int = 0,
    val id_tecnica_obra: Int = 0,
    val nombre_obra: String = "",
    val fecha_obra: String =  "",
    val dimensiones_obra: String = "",

    val tecnicasLista: List<Tecnica> = emptyList(),

    val tempPhotoUri: Uri? = null,
    val photoUploadState: PhotoUploadState = PhotoUploadState.Idle,
    val fotoObraUrl: String = "",

    val isLoading: Boolean = true,
    val error: String? = null,
)

sealed class PhotoUploadState {
    object Idle : PhotoUploadState()
    object Loading : PhotoUploadState()
    data class Success(val downloadUrl: String) : PhotoUploadState()
    data class Error(val message: String) : PhotoUploadState()
}