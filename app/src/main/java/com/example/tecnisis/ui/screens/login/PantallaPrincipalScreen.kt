package com.example.tecnisis.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tecnisis.R
import com.example.tecnisis.ui.components.Cabecera

@Composable
fun PantallaPrincipal (
    modifier: Modifier = Modifier,
    listaOpciones: List<String> = listOf("opcion1", "opcion2", "opcion3"),
) {
    Box(
        modifier = modifier.padding(vertical = 30.dp, horizontal = 15.dp).fillMaxSize().background(
            MaterialTheme.colorScheme.background)
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Cabecera(
                modifier = modifier.padding(vertical = 10.dp).fillMaxWidth()
            )
            Spacer(modifier = modifier.height(1.dp))
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Bienvenida()
                Spacer(modifier = modifier.height(30.dp))
                Perfil()
                Spacer(modifier = modifier.height(30.dp))
                Opciones()
            }
        }
    }
}

@Composable
fun Perfil(modifier: Modifier = Modifier) {
    ElevatedCard (
        modifier = modifier.width(250.dp).height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.maxresdefault)
                , contentDescription = "lo que sea"
            )
            Box(modifier= Modifier.fillMaxSize(), contentAlignment = Alignment.Center ) {
                Text(
                    text = "Administrador"
                )
            }
        }
    }
}

@Composable
fun Opciones(modifier: Modifier = Modifier, listaOpciones: List<String> = listOf("opcion1", "opcion2", "opcion3")) {
    Card(
        modifier = modifier.fillMaxWidth().padding(horizontal = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        LazyColumn() {
            items(listaOpciones) {
                CuadroOpciones(it)
            }
        }
    }
}

@Composable
fun CuadroOpciones( textoOpcion: String ) {
    Card (
        modifier = Modifier.height(100.dp).padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ){
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = textoOpcion,
            )
        }
    }
}

@Composable
fun Bienvenida(modifier: Modifier = Modifier) {
    Card (
        modifier = modifier.fillMaxWidth().height(60.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Bienvenido Juan",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}