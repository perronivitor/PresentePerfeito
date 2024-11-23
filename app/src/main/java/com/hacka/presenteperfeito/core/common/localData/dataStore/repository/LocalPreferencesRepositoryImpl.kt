package com.hacka.presenteperfeito.core.common.localData.dataStore.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

private const val DEFAULT_PREFERENCES = "DEFAULT_PREFERENCES"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DEFAULT_PREFERENCES)

@Single
class LocalPreferencesRepositoryImpl(private val context: Context): LocalPreferencesRepository {
    override suspend fun <T : Any> setData(id: String, data: T) {
        context.dataStore.edit { settings ->
            val preferences = when(data){
                is String -> stringPreferencesKey(id)
                else -> throw IllegalArgumentException("Invalid data type")
            }
            settings[preferences] = data
        }
    }

    override suspend fun getStringData(id: String): Flow<String?> = context.dataStore.data.map { settings ->
        val preferences = stringPreferencesKey(id)
        return@map settings[preferences]
    }

    override suspend fun clearData() {
        context.dataStore.edit {
            it.clear()
        }
    }
}