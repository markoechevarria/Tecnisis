package com.example.tecnisis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tecnisis.navigation.AppNavGraph
import com.example.tecnisis.ui.theme.TecnisisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TecnisisTheme {
                AppNavGraph()
            }
        }
    }
}