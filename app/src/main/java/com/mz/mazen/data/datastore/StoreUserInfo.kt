package com.mz.mazen.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class StoreUserInfo(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserEmail")
        val USER_FIRST_NAME_KEY = stringPreferencesKey("first_name")
        val USER_LAST_NAME_KEY = stringPreferencesKey("last_name")
        val USER_HEIGHT_KEY = stringPreferencesKey("height")
        val USER_WEIGHT_KEY = stringPreferencesKey("weight")
    }

    val getFirstName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_FIRST_NAME_KEY] ?: ""
        }

    suspend fun saveFirstName(lastName: String){
        context.dataStore.edit { preferences ->
            preferences[USER_LAST_NAME_KEY] = lastName
        }
    }
    val getLastName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_FIRST_NAME_KEY] ?: ""
        }

    suspend fun saveLastName(lastName: String){
        context.dataStore.edit { preferences ->
            preferences[USER_LAST_NAME_KEY] = lastName        }
    }
    val getHeight: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_HEIGHT_KEY] ?: ""
        }

    suspend fun saveHeightName(height: String){
        context.dataStore.edit { preferences ->
            preferences[USER_HEIGHT_KEY] = height        }
    }
    val getWeight: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_FIRST_NAME_KEY] ?: ""
        }

    suspend fun saveWeight(weight: String){
        context.dataStore.edit { preferences ->
            preferences[USER_WEIGHT_KEY] = weight        }
    }

}





























