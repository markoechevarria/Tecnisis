package com.example.tecnisis.ui.views.evaluadorArtistico.solicitudesRegistradasEvaluadorArtistico

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tecnisis.R

@Composable
fun PantallaSolicitudesRegistradasEvaluadorArtistico(
    id: Int,
    id_perfil: Int,
    verDetalleSolicitud: (Int, Int, Int) -> Unit,
    solicitudesRegistradasViewModelEvaluadorArtistico: SolicitudesRegistradasViewModelEvaluadorArtistico = hiltViewModel(),
) {

    val solicitudesRegistradasUiStateEvaluadorArtistico by solicitudesRegistradasViewModelEvaluadorArtistico.uiState.collectAsState()

    LaunchedEffect(id, id_perfil) {
        solicitudesRegistradasViewModelEvaluadorArtistico.actualizarDatos(id, id_perfil)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
            IconButton( onClick = {}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Solicitudes Registradas", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (solicitudesRegistradasUiStateEvaluadorArtistico.listaSolicitudes.size != 0) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().height(640.dp)
            ) {
                items(solicitudesRegistradasUiStateEvaluadorArtistico.listaSolicitudes) { solicitud ->
                    solicitudesRegistradasViewModelEvaluadorArtistico.obtenerDatosExtra(solicitud)
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                        onClick = { verDetalleSolicitud(solicitud.id, id, id_perfil) }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Column {
                                Text(
                                    text = solicitudesRegistradasUiStateEvaluadorArtistico.solicitudDatosArtista.nombre,
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = solicitudesRegistradasUiStateEvaluadorArtistico.solicitudDatosArtista.nombre_artista,
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = solicitudesRegistradasUiStateEvaluadorArtistico.solicitudDatosArtista.fecha,
                                    style = MaterialTheme.typography.labelSmall
                                )
                                Text(
                                    text = solicitudesRegistradasUiStateEvaluadorArtistico.solicitudDatosArtista.tecnica,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.Face,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }
        } else {
            Text( text = "No tiene solicitudes asignadas")
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