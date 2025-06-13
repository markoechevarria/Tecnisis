package com.example.tecnisis.ui.casosDeUso.gerente.nuevaTecnica

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.tecnisis.navigation.Rutas

@Composable
fun PantallaNuevaTecnica(
    navController: NavController
) {
    var nombre by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var apreciacion by remember { mutableStateOf("") }

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
            IconButton(onClick = {navController.navigate(Rutas.INICIO)}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Nueva Técnica", style = MaterialTheme.typography.titleMedium)
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
                Text("Ingresar Datos", style = MaterialTheme.typography.titleSmall)

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre técnica") },
                    leadingIcon = { Icon(Icons.Default.Edit, null) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = valor,
                    onValueChange = { valor = it },
                    label = { Text("Valor aproximado") },
                    leadingIcon = { Icon(Icons.Default.Face, null) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = apreciacion,
                    onValueChange = { apreciacion = it },
                    label = { Text("Nivel de apreciación") },
                    leadingIcon = { Icon(Icons.Default.Star, null) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {navController.navigate(Rutas.INICIO)},
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
