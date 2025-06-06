package com.example.tecnisis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.navigation.compose.rememberNavController
import com.example.tecnisis.ui.screens.login.LoginScreen
import com.example.tecnisis.ui.screens.login.PantallaPrincipal
import com.example.tecnisis.ui.screens.login.SeleccionPerfilScreen
import com.example.tecnisis.ui.theme.TecnisisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TecnisisTheme {
                PantallaPrincipal()
            }
        }
    }
}