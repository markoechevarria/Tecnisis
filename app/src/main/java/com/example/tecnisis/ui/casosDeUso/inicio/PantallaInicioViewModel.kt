package com.example.tecnisis.ui.casosDeUso.inicio

import androidx.lifecycle.ViewModel
import com.example.tecnisis.data.ListaOpciones
import com.example.tecnisis.data.ListaPerfiles
import com.example.tecnisis.data.Opcion
import com.example.tecnisis.data.Usuario
import com.example.tecnisis.data.listaUsuarios
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PantallaInicioViewModel  :ViewModel() {
    private val _uiState = MutableStateFlow(PantallaInicioUiState() )
    val uiState: StateFlow<PantallaInicioUiState> = _uiState.asStateFlow()

    fun asignarIds(id: Int, id_perfil: Int) {
        _uiState.update { currentState -> currentState.copy(id = id, id_perfil = id_perfil) }
        asignarOpciones()
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
        val usuario = listaUsuarios.usuarios.find { usuario -> usuario.id == _uiState.value.id } // obtner usuario por id
        val opciones: List<Opcion> = ListaOpciones.opciones.filter { opcion -> opcion.id_perfil == usuario?.id_perfil } // obtener opciones por id del usuario
        _uiState.update { currentState -> currentState.copy(opciones = opciones)}
    }
}