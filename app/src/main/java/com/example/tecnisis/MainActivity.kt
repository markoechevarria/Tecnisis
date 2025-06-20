package com.example.tecnisis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.tecnisis.navigation.AppNavGraph
import com.example.tecnisis.navigation.Rutas
import com.example.tecnisis.ui.theme.TecnisisTheme
import com.example.tecnisis.data.UserPreferences

class MainActivity : ComponentActivity() {

    // private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // auth = Firebase.auth
        setContent {
            TecnisisTheme {
                val navController = rememberNavController()
                val userPreferences = UserPreferences(applicationContext)
                val userId by userPreferences.userIdFlow.collectAsState(initial = null)
                
                // Mejorada la lógica de navegación inicial
                LaunchedEffect(userId) {
                    if (userId != null) {
                        // Si hay sesión activa, ir a inicio
                        navController.navigate(Rutas.INICIO) {
                            // Limpiar todo el stack de navegación
                            popUpTo(0) { inclusive = true }
                        }
                    } else {
                        // Si no hay sesión, ir a login
                        navController.navigate(Rutas.LOGIN) {
                            // Limpiar todo el stack de navegación
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }
                
                AppNavGraph(navController = navController, userPreferences = userPreferences)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Comentamos temporalmente la verificación de autenticación
        /*
        val currentUser = auth.currentUser
        if(currentUser!=null){
            //navegar a home
        }
        */
    }
}






