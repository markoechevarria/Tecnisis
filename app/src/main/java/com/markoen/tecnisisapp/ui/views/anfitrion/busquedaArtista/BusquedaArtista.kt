package com.markoen.tecnisisapp.ui.views.anfitrion.busquedaArtista

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.markoen.tecnisisapp.R

@Composable
fun PantallaBusquedaArtista(
    id: Int,
    id_perfil: Int,
    navegarRegistarArtista: (Int, Int) -> Unit,
    navegarRegistrarObra: (Int, Int, Int) -> Unit,
    busquedaArtistaViewModel: BusquedaArtistaViewModel = hiltViewModel(),
    navegarAtras: () -> Unit
) {
    val busquedaArtistaUiState by busquedaArtistaViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

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
            IconButton(modifier = Modifier, onClick = { navegarAtras() } ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Búsqueda de artista", style = MaterialTheme.typography.titleMedium)
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
                Text("Ingresar DNI:", style = MaterialTheme.typography.bodyLarge)

                OutlinedTextField(
                    value = busquedaArtistaUiState.dni,
                    onValueChange = { busquedaArtistaViewModel.actualizarDni(it) },
                    label = { Text("DNI") },
                    leadingIcon = { Icon(Icons.Default.Person, null) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(onNext = { defaultKeyboardAction(ImeAction.Next) }),
                )

                if (busquedaArtistaUiState.seEncontro) {
                    Text("Se encontró:", style = MaterialTheme.typography.bodyLarge)
                }
                if (busquedaArtistaUiState.seEncontro == false && busquedaArtistaUiState.dni == "") {
                    Text("No ha ingresado DNI:", style = MaterialTheme.typography.bodyLarge)
                }
                if (busquedaArtistaUiState.seEncontro == false && busquedaArtistaUiState.dni != "") {
                    Text("No se encontró registro", style = MaterialTheme.typography.bodyLarge)
                }

                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        if (busquedaArtistaUiState.seEncontro) {
                            Text( busquedaArtistaUiState.nombreArtista, style = MaterialTheme.typography.titleMedium)
                        }
                        if (busquedaArtistaUiState.seEncontro == false && busquedaArtistaUiState.dni == "") {
                            Text("Dni no ingresado", style = MaterialTheme.typography.titleMedium)
                        }
                        if (busquedaArtistaUiState.seEncontro == false && busquedaArtistaUiState.dni != "") {
                            Text("DNI no encontrado", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = {
                            if (busquedaArtistaUiState.cantDigitos >= 8) {
                                busquedaArtistaViewModel.actualizarSeEncontroNombreArtista()
                            }
                        },
                        colors =  if (busquedaArtistaUiState.cantDigitos >= 8){
                            ButtonDefaults.buttonColors()} else {
                            ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Verificar Dni")
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { if (busquedaArtistaUiState.seEncontro) {navegarRegistrarObra(id, id_perfil, busquedaArtistaUiState.id)}
                                  else if (busquedaArtistaUiState.habilitadoBotonArtista == false) {  } },
                        colors =
                            if ( busquedaArtistaUiState.seEncontro ) { ButtonDefaults.buttonColors() }
                            else if (busquedaArtistaUiState.habilitadoBotonArtista == false){
                                ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary,
                                    contentColor = MaterialTheme.colorScheme.onTertiary) }
                            else { ButtonDefaults.buttonColors() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Registrar obra")
                    }
                    OutlinedButton(
                        onClick = { if (busquedaArtistaUiState.habilitadoBotonArtista == false) {navegarRegistarArtista(id, id_perfil )}
                        else if (busquedaArtistaUiState.seEncontro) {  } },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Registrar artista")
                    }

                }
            }
        }
1
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary).clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
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