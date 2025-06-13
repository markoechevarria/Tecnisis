package com.example.tecnisis.ui.casosDeUso.anfitrion.listarExpertosDisponibles

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tecnisis.navigation.Rutas

@Composable
fun PantallaListarExpertosDisponibles (
    navController: NavController,
    expertos: List<String> = listOf("Picasso", "Dali", "Van Gogh", "Da Vinci", "Rembrandt", "Monet"),
    listarExpertosDisponiblesViewModel: ListarExpertosDisponiblesViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val listarExpertosDisponiblesUiState by listarExpertosDisponiblesViewModel.uiState.collectAsState()

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
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(48.dp)
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
            IconButton( onClick = {navController.navigate(Rutas.REGISTRAR_OBRA)}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Expertos disponibles", style = MaterialTheme.typography.titleMedium)
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                expertos.forEachIndexed { index, nombre->
                    cartaExperto( nombre, index , ( index == listarExpertosDisponiblesUiState.expertoSeleccionado ), { listarExpertosDisponiblesViewModel.elegirExperto(it) } )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (listarExpertosDisponiblesUiState.habilitadoBoton == false) {
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Asignar experto")
                    }
                } else {
                    Button(
                        onClick = { navController.navigate(Rutas.CONFIRMAR_SOLICITUD) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Asignar experto")
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
}

@Composable
fun cartaExperto( nombreExperto: String, numeroExperto: Int, seleccionado: Boolean, selecionarExperto: (Int) -> Unit ) {
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(
            containerColor = when {
                seleccionado -> MaterialTheme.colorScheme.secondaryContainer
                else -> MaterialTheme.colorScheme.primaryContainer
            }
        ),
        onClick = { selecionarExperto(numeroExperto) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = null,
                tint = if (seleccionado) MaterialTheme.colorScheme.onSecondaryContainer
                else MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = nombreExperto,
                style = MaterialTheme.typography.titleMedium,
                color = if (seleccionado) MaterialTheme.colorScheme.onSecondaryContainer
                else MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}