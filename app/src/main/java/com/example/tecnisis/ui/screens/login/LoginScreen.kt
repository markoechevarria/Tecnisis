package com.example.tecnisis.ui.screens.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tecnisis.R
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.tecnisis.navigation.Rutas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



@Composable
fun LoginScreen(
    navController: NavController,
    auth: FirebaseAuth
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Bienvenido a TECNISIS",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Gestión de exposiciones de arte",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            errorMessage = "" // Limpiar error al escribir
                        },
                        label = { Text("Correo electrónico") },
                        leadingIcon = { Icon(Icons.Default.Email, null) },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                        isError = errorMessage.isNotEmpty()
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorMessage = "" // Limpiar error al escribir
                        },
                        label = { Text("Contraseña") },
                        leadingIcon = { Icon(Icons.Default.Lock, null) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                        isError = errorMessage.isNotEmpty()
                    )

                    // Mostrar mensaje de error si existe
                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Button(
                        onClick = {
                            // Validaciones básicas
                            if (email.isBlank()) {
                                errorMessage = "Por favor ingresa tu correo electrónico"
                                return@Button
                            }
                            if (password.isBlank()) {
                                errorMessage = "Por favor ingresa tu contraseña"
                                return@Button
                            }
                            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                errorMessage = "Por favor ingresa un correo válido"
                                return@Button
                            }

                            isLoading = true
                            errorMessage = ""

                            // USAR signInWithEmailAndPassword para LOGIN
                            Log.d("aris", "Intentando login con: $email")
                            auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    isLoading = false
                                    Log.d("aris", "Login completado. ¿Exitoso? ${task.isSuccessful}")

                                    if (task.isSuccessful) {
                                        Log.i("aris", "LOGIN OK - Usuario autenticado correctamente")
                                        Log.d("aris", "Navegando a SELECCION_PERFIL")
                                        // Navegar a la siguiente pantalla
                                        navController.navigate(Rutas.SELECCION_PERFIL)
                                    } else {
                                        val exception = task.exception
                                        Log.e("aris", "LOGIN ERROR: ${exception?.message}")
                                        Log.e("aris", "LOGIN ERROR TYPE: ${exception?.javaClass?.simpleName}")

                                        // Mensajes de error más específicos
                                        errorMessage = when {
                                            exception?.message?.contains("badly formatted") == true -> "Formato de correo inválido"
                                            exception?.message?.contains("no user record") == true -> "Usuario no encontrado"
                                            exception?.message?.contains("password is invalid") == true -> "Contraseña incorrecta"
                                            exception?.message?.contains("temporarily disabled") == true -> "Cuenta temporalmente bloqueada"
                                            exception?.message?.contains("network error") == true -> "Error de conexión"
                                            else -> "Error: ${exception?.message ?: "Error desconocido"}"
                                        }
                                    }
                                }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            Text("Iniciando sesión...")
                        } else {
                            Text("Iniciar sesión")
                        }
                    }

                    TextButton(
                        onClick = {
                            // Aquí puedes navegar a una pantalla de registro
                            // navController.navigate(Rutas.REGISTRO)
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        enabled = !isLoading
                    ) {
                        Text("¿No tienes cuenta? Regístrate")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "© 2025 Todos los derechos reservados",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
        }
    }
}
