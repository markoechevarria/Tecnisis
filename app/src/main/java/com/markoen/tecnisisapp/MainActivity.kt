package com.markoen.tecnisisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.markoen.tecnisisapp.navigation.NavigationApp
import com.markoen.tecnisisapp.ui.theme.TecnisisTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TecnisisTheme {
                NavigationApp()
            }
        }
    }
}