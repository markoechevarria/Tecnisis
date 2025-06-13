package com.example.tecnisis.ui.casosDeUso.inicio

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tecnisis.navigation.Rutas

@Composable
fun PantallaInicio( navController: NavController ) {
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "TECNISIS",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Bienvenido Marko",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Avatar",
                modifier = Modifier.size(96.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Has iniciado sesión como:", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Administrador",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            crearCarta("Busqueda Artista", rutaNavegacion = Rutas.BUSQUEDA_ARTISTA, navController)
            crearCarta("Solicitudes Registradas", rutaNavegacion = Rutas.SOLICITUDES_REGISTRADAS, navController)
            crearCarta("Obras aprobadas para evaluacion Economica", rutaNavegacion = Rutas.LISTA_OBRAS_APROBADAS, navController)
            crearCarta("Gestion Tecnicas", rutaNavegacion = Rutas.GESTION_TECNICAS, navController)
            crearCarta("Gestion Expertos", rutaNavegacion = Rutas.GESTION_EXPERTOS, navController)
            crearCarta("Reportes", rutaNavegacion = Rutas.DASHBOARD_REPORTES, navController)
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
fun crearCarta(texto: String, rutaNavegacion: String, navController: NavController) {
    ElevatedCard(
        onClick = { navController.navigate(rutaNavegacion) },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(texto, style = MaterialTheme.typography.titleMedium)
        }
    }
}