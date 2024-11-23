package com.hacka.presenteperfeito.core.network.interceptor

import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.LocalPreferencesRepository
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.TOKEN
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class SessionInterceptor(private val localPreferencesRepository: LocalPreferencesRepository) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token =
            runBlocking { localPreferencesRepository.getStringData(TOKEN).firstOrNull() }
        return if (token.isNullOrEmpty()) {
            chain.proceed(chain.request())
        } else chain.proceed(request.header("Authorization", "Bearer $token").build())
    }
}