package com.markoen.tecnisisapp.ui.views.evaluadorEconomico.evaluacionEconomica

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.markoen.tecnisisapp.R

@Composable
fun PantallaEvaluacionEconomica(
    id: Int,
    id_perfil: Int,
    id_solicitud: Int,
    navegarInicio: (Int, Int) -> Unit,
    evaluacionEconomicaViewModel: EvaluacionEconomicaViewModel = hiltViewModel(),
    navegarAtras: () -> Unit
) {
    val evaluacionEconomicaUiState by evaluacionEconomicaViewModel.uiState.collectAsState()

    evaluacionEconomicaViewModel.asignarIds( id, id_perfil, id_solicitud)

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
            IconButton( onClick = { navegarAtras() } ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Evaluación Socioeconómica", style = MaterialTheme.typography.titleMedium)
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
                Text("Obra", style = MaterialTheme.typography.titleSmall)
                InfoCardObra(
                    nombre = evaluacionEconomicaUiState.nombre_obra,
                    tecnica=evaluacionEconomicaUiState.tecnica,
                    fecha=evaluacionEconomicaUiState.fecha ,
                    dimensiones=evaluacionEconomicaUiState.dimensiones
                )

                Text("Experto", style = MaterialTheme.typography.titleSmall)
                InfoCardExperto( nombre = evaluacionEconomicaUiState.nombre_experto )

                Text("Ver obra", style = MaterialTheme.typography.titleSmall)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen obra", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                OutlinedTextField(
                    value = evaluacionEconomicaUiState.precioVenta.toString(),
                    onValueChange ={it ->  evaluacionEconomicaViewModel.asignarVenta( venta = it.toInt() )} ,
                    label = { Text("Precio de venta") },
                    leadingIcon = { Icon(Icons.Default.ShoppingCart, null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(onNext = { defaultKeyboardAction(ImeAction.Next) }),
                )

                OutlinedTextField(
                    value = evaluacionEconomicaUiState.porcentajeGanancia.toString(),
                    onValueChange = {it ->  evaluacionEconomicaViewModel.asignarPorcentajeVenta( porcentaje = it.toInt() )},
                    label = { Text("Porcentaje de ganancia") },
                    leadingIcon = { Icon(Icons.Default.ShoppingCart, null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(onNext = { defaultKeyboardAction(ImeAction.Done) }),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { evaluacionEconomicaViewModel.cambiarVentanaAprobado(); evaluacionEconomicaViewModel.asignarAprobacion(true) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Registrar")
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
    if (evaluacionEconomicaUiState.showDialogAprobado) {
        AlertDialog(
            onDismissRequest = { evaluacionEconomicaViewModel.cambiarVentanaAprobado(); navegarInicio(id, id_perfil) },
            text = { Text("Evaluacion Registrada.") },
            confirmButton = { Button( onClick = { evaluacionEconomicaViewModel.cambiarVentanaAprobado(); navegarInicio(id, id_perfil) }) { Text("Aceptar") } },
        )
    }
}

@Composable
fun InfoCardObra(nombre: String, tecnica: String, fecha: String, dimensiones: String) {
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
fun InfoCardExperto(nombre: String) {
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
        }
    }
}


@Preview(showBackground = true, heightDp = 910)
@Composable
fun PreviewPantallaEvaluacionEconomica() {
    MaterialTheme {
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
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Evaluación Socioeconómica", style = MaterialTheme.typography.titleMedium)
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
                    Text("Obra", style = MaterialTheme.typography.titleSmall)
                    InfoCardObra(
                        nombre = "Obra de ejemplo",
                        tecnica = "Óleo sobre lienzo",
                        fecha = "12/04/2025",
                        dimensiones = "50x70 cm"
                    )

                    Text("Experto", style = MaterialTheme.typography.titleSmall)
                    InfoCardExperto(nombre = "Dra. Rosario Gutierrez")

                    Text("Ver obra", style = MaterialTheme.typography.titleSmall)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Imagen obra", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }

                    OutlinedTextField(
                        value = "1200",
                        onValueChange = {},
                        label = { Text("Precio de venta") },
                        leadingIcon = { Icon(Icons.Default.ShoppingCart, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions.Default
                    )

                    OutlinedTextField(
                        value = "15",
                        onValueChange = {},
                        label = { Text("Porcentaje de ganancia") },
                        leadingIcon = { Icon(Icons.Default.ShoppingCart, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions.Default
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Registrar")
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
}