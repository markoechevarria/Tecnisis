package com.example.tecnisis.ui.casosDeUso.inicio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnisis.data.ListaOpciones
import com.example.tecnisis.data.ListaPerfiles
import com.example.tecnisis.data.Opcion
import com.example.tecnisis.data.Usuario
import com.example.tecnisis.data.listaUsuarios
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaInicioViewModel @Inject constructor(
    private val usuarioRepository: InterfazUsuarioRepository
) :ViewModel() {
    private val _uiState = MutableStateFlow(PantallaInicioUiState() )
    val uiState: StateFlow<PantallaInicioUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy(id = id, id_perfil = id_perfil) }
        //asignarOpciones()
    }
    fun obtenerUsuario(): Usuario {
        val usuario = listaUsuarios.usuarios.find { usuario -> usuario.id == _uiState.value.id } ?: Usuario(0,0,"","","")
        return usuario
    }
    fun obtenerPerfil(): String {
        val perfil = ListaPerfiles.perfiles.find { it -> it.id == obtenerUsuario().id_perfil }?.nombre ?: ""
        return perfil
    }
    fun asignarOpciones() {
        viewModelScope.launch {
            try {
                Log.d("viewmodelpantallainico", _uiState.value.id_perfil.toString())
                Log.d("viewmodelpantallainico", "entrando al try")
                val lista_opciones = usuarioRepository.obtenerOpciones( _uiState.value.id_perfil)
                Log.d("viewmodelpantallainico", "despues de hacer la llamada a la api")
                _uiState.update { currentState -> currentState.copy(
                    opciones = lista_opciones
                ) }
            } catch ( e: Exception ) {
                Log.d("viewmodelpantallainico", "entro y agarro al catch")
                Log.d("viewmodel", e.message.toString())
            }
        }
    }
    /*
    fun asignarOpciones() {
        val usuario = listaUsuarios.usuarios.find { usuario -> usuario.id == _uiState.value.id } // obtner usuario por id
        val opciones: List<Opcion> = ListaOpciones.opciones.filter { opcion -> opcion.id_perfil == usuario?.id_perfil } // obtener opciones por id del usuario
        _uiState.update { currentState -> currentState.copy(opciones = opciones)}
    }
     */
}