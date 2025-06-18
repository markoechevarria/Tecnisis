package com.example.tecnisis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.tecnisis.navigation.AppNavGraph
import com.example.tecnisis.ui.theme.TecnisisTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    // private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // auth = Firebase.auth
        setContent {
            TecnisisTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
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






