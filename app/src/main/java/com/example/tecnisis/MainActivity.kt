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
                LaunchedEffect(userId) {
                    if (userId != null) {
                        navController.navigate(Rutas.INICIO) {
                            popUpTo(0)
                        }
                    } else {
                        navController.navigate(Rutas.LOGIN) {
                            popUpTo(0)
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






