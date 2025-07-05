package com.markoen.tecnisisapp.ui.views.anfitrion.solicitudesRegistradasAnfitrion

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.markoen.tecnisisapp.R
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog

@Composable
fun PantallaSolicitudesRegistradasAnfitrion(
    id: Int,
    id_perfil: Int,
    solicitudesRegistradasViewModelAnfitrion: SolicitudesRegistradasViewModelAnfitrion = hiltViewModel(),
    navegarAtras: () -> Unit
) {
    val solicitudesRegistradasUiStateAnfitrion by solicitudesRegistradasViewModelAnfitrion.uiState.collectAsState()
    val scrollState = rememberScrollState()

    solicitudesRegistradasViewModelAnfitrion.obtenerSolicitudes()

    if (solicitudesRegistradasUiStateAnfitrion.isLoading) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                modifier = Modifier.size(100.dp).background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.image_fx_redondeada) ,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "TECNISIS",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clickable {  }
        ) {
            IconButton( onClick = { navegarAtras() } ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Solicitudes Registradas", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth().height(640.dp)
        ) {
            items( solicitudesRegistradasUiStateAnfitrion.listaSolicitudesExtra ) { it ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    onClick = {}
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 32.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            texto( "Nombre de la Obra: ", it.first.nombre )
                            texto( "Nombre del artista: ", it.second.nombre )
                            texto( "Fecha de la obra: ", it.first.fecha )
                            texto( "Dimensiones: ", it.first.dimensiones)
                        }

                        Icon(
                            painter = painterResource( R.drawable.icono_obra ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(150.dp).padding(start = 12.dp)
                        )
                    }
                }
            }
        }

        Spacer( modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "© 2025 Todos los derechos reservados",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
fun texto( leyenda: String, mensaje: String ) {
    Text(
        text = leyenda,
        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = mensaje,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
    Spacer(modifier = Modifier.height(8.dp))
}