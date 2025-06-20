package com.example.tecnisis.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UserPreferencesKeys {
    val USER_ID = stringPreferencesKey("user_id")
    val USER_TYPE = stringPreferencesKey("user_type")
}

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {
    val userIdFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[UserPreferencesKeys.USER_ID]
    }

    //FLUJO QUE OBSERVA CAMBIOS ES EL Flow String (DATASTORE) aqui se guarda el tipo de usuario (USER_TYPE = TIPO DE USUARIO DE LA BD)
    val userTypeFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[UserPreferencesKeys.USER_TYPE]
    }

    suspend fun saveUserSession(userId: String, userType: String) {
        context.dataStore.edit { prefs ->
            prefs[UserPreferencesKeys.USER_ID] = userId
            prefs[UserPreferencesKeys.USER_TYPE] = userType //lECTURA ESPECIFICA (USER_TYPE = TIPO DE USUARIO DE LA BD)
        }
    }

    suspend fun clearUserSession() {
        context.dataStore.edit { prefs ->
            prefs.remove(UserPreferencesKeys.USER_ID)
            prefs.remove(UserPreferencesKeys.USER_TYPE)
        }
    }
} 