package com.markoen.tecnisisapp.ui.views.evaluadorArtistico.detalleSolicitud

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.markoen.tecnisisapp.R

@Composable
fun PantallaDetalleSolicitudEvaluadorArtistico(
    id_solicitud: Int = 0,
    id_usuario: Int = 0,
    id_perfil: Int = 0,
    navegarEvaluacion: (Int, Int, Int) -> Unit,
    detalleSolicitudViewModelEvaluadorArtistico: DetalleSolicitudViewModelEvaluadorArtistico = hiltViewModel(),
    navegarAtras: () -> Unit
) {
    val detalleSolicitudUiStateEvaluadorArtistico by detalleSolicitudViewModelEvaluadorArtistico.uiState.collectAsState()
    val scrollState = rememberScrollState()

    detalleSolicitudViewModelEvaluadorArtistico.asignarIds(id_solicitud, id_usuario, id_perfil)

    if (detalleSolicitudUiStateEvaluadorArtistico.isLoading) {
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
                .clickable { }
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
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Datos Artista", style = MaterialTheme.typography.titleSmall)
                InfoCardArtista(
                    detalleSolicitudUiStateEvaluadorArtistico.dni,
                    detalleSolicitudUiStateEvaluadorArtistico.nombre,
                    detalleSolicitudUiStateEvaluadorArtistico.direccion,
                    detalleSolicitudUiStateEvaluadorArtistico.telefono
                )

                Text("Datos Obra", style = MaterialTheme.typography.titleSmall)
                InfoCardObra(
                    detalleSolicitudUiStateEvaluadorArtistico.tecnica,
                    detalleSolicitudUiStateEvaluadorArtistico.fecha,
                    detalleSolicitudUiStateEvaluadorArtistico.dimensiones
                )

                Text("Datos Experto", style = MaterialTheme.typography.titleSmall)
                InfoCardExperto(
                    detalleSolicitudUiStateEvaluadorArtistico.nombre,
                    detalleSolicitudUiStateEvaluadorArtistico.dni
                )

                Text("Imagen Obra", style = MaterialTheme.typography.titleSmall)
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(
                        modifier = Modifier.padding(4.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CargarImagenConCoil( detalleSolicitudUiStateEvaluadorArtistico.obraurl )
                    }
                }

                Text("Estado Solicitud", style = MaterialTheme.typography.titleSmall)
                InfoCardEstado(
                    if (detalleSolicitudUiStateEvaluadorArtistico.estadoSolicitudEconomico) {"Aprobado"} else {"Pendiente"}
                )



            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (detalleSolicitudUiStateEvaluadorArtistico.estadoSolicitudEconomico) {
            Row( modifier = Modifier, horizontalArrangement = Arrangement.SpaceBetween ) {
                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Ya Evaluado")
                }
            }
        } else {
            Row( modifier = Modifier, horizontalArrangement = Arrangement.SpaceBetween ) {
                Button(
                    onClick = { navegarEvaluacion(id_solicitud, id_usuario, id_perfil) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Evaluar")
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
}

@Composable
fun InfoCardArtista(dni: String, nombre: String, direccion: String, telefono: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = dni,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = nombre,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = direccion,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = telefono,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

    }
}
@Composable
fun InfoCardObra(tecnica: String, fecha: String, dimensiones: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = tecnica,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = fecha,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = dimensiones,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

    }
}
@Composable
fun InfoCardExperto(nombre: String, dni: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = dni,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

    }
}
@Composable
fun InfoCardEstado(estado: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column (
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = estado,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

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

@Preview(showBackground = true, heightDp = 1200)
@Composable
fun PreviewPantallaDetalleSolicitudEvaluadorArtistico() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
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
                        painter = painterResource(R.drawable.image_fx_redondeada),
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
            ) {
                IconButton(onClick = {}) {
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
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Datos Artista", style = MaterialTheme.typography.titleSmall)
                    InfoCardArtista("12345678", "Juan Pérez", "Calle Falsa 123", "999888777")

                    Text("Datos Obra", style = MaterialTheme.typography.titleSmall)
                    InfoCardObra("Óleo", "2025-07-10", "100x120 cm")

                    Text("Datos Experto", style = MaterialTheme.typography.titleSmall)
                    InfoCardExperto("Juan Pérez", "12345678")

                    Text("Imagen Obra", style = MaterialTheme.typography.titleSmall)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icono_obra),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(150.dp)
                                .padding(start = 12.dp)
                        )
                    }

                    Text("Estado Solicitud", style = MaterialTheme.typography.titleSmall)
                    InfoCardEstado("Pendiente")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Evaluar")
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
    }
}