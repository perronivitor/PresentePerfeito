package com.hacka.presenteperfeito.core.common.localData.dataStore.repository

import kotlinx.coroutines.flow.Flow

interface LocalPreferencesRepository {
    suspend fun <T : Any> setData(id: String, data: T)
    suspend fun getStringData(id: String): Flow<String?>
    suspend fun clearData()
}