package com.example.tecnisis.paquetedepruebahilt

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import com.example.tecnisis.paquetedepruebahilt.GreetingService

import javax.inject.Inject

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity() {

    // Inyectamos nuestro GreetingService
    @Inject
    lateinit var greetingService: GreetingService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this)
        textView.text = greetingService.getGreeting()
        setContentView(textView)
    }
}