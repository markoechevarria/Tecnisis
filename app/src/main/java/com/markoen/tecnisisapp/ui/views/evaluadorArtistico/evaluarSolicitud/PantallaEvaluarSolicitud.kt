package com.markoen.tecnisisapp.ui.views.evaluadorArtistico.evaluarSolicitud

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.markoen.tecnisisapp.R

@Composable
fun PantallaEvaluarSolicitud(
    id_solicitud: Int,
    id_perfil: Int,
    id_usuario: Int,
    navegarInicio: (Int, Int) -> Unit,
    pantallaEvaluarSolicitudViewModel: PantallaEvaluarSolicitudViewModel = hiltViewModel(),
    navegarAtras: () -> Unit
) {
    val pantallaEvaluarSolicitudUiState by pantallaEvaluarSolicitudViewModel.uiState.collectAsState()
    pantallaEvaluarSolicitudViewModel.asignarIds(id_usuario, id_perfil, id_solicitud)

    if (pantallaEvaluarSolicitudUiState.isLoading) {
        Dialog(
            onDismissRequest = {  },
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
            IconButton(onClick = { navegarAtras() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Detalle de solicitud", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                CargarImagenConCoil( pantallaEvaluarSolicitudUiState.url_obra)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen de obra", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                Spacer(modifier = Modifier.height(1.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { pantallaEvaluarSolicitudViewModel.cambiarVentanaAprobado(); pantallaEvaluarSolicitudViewModel.asignarAprobacion(true) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Aprobar")
                    }

                    OutlinedButton(
                        onClick = { pantallaEvaluarSolicitudViewModel.cambiarVentanaDesaprobado(); pantallaEvaluarSolicitudViewModel.asignarAprobacion(false) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Rechazar")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

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

    if (pantallaEvaluarSolicitudUiState.showDialogAprobado) {
        AlertDialog(
            onDismissRequest = { pantallaEvaluarSolicitudViewModel.cambiarVentanaAprobado(); navegarInicio(id_usuario, id_perfil) },
            text = { Text("Evaluacion Registrada.") },
            confirmButton = { Button( onClick = { pantallaEvaluarSolicitudViewModel.cambiarVentanaAprobado(); navegarInicio(id_usuario, id_perfil) }) { Text("Aceptar") } },
        )
    }
    if (pantallaEvaluarSolicitudUiState.showDialogDesaprobado) {
        AlertDialog(
            onDismissRequest = { pantallaEvaluarSolicitudViewModel.cambiarVentanaDesaprobado(); navegarInicio(id_usuario, id_perfil) },
            text = { Text("Evaluacion Desestimada.") },
            confirmButton = { Button( onClick = { pantallaEvaluarSolicitudViewModel.cambiarVentanaDesaprobado(); navegarInicio(id_usuario, id_perfil) }) { Text("Aceptar") } },
        )
    }
}

@Composable
fun CargarImagenConCoil(imageUrl: String?) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Descripción de la imagen",
        modifier = Modifier.size(200.dp),
    )
}