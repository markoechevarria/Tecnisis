package com.example.tecnisis

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("TecnisisApp", "Aplicación Hilt iniciada!")
    }
}