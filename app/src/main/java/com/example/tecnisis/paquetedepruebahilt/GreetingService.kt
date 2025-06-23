package com.example.tecnisis.paquetedepruebahilt

import javax.inject.Inject
import javax.inject.Singleton

interface GreetingService {
    fun getGreeting(): String
}

@Singleton
class GreetingServiceImpl @Inject constructor() : GreetingService {
    override fun getGreeting(): String {
        return "¡Hola desde Hilt! La inyección de dependencias funciona."
    }
}