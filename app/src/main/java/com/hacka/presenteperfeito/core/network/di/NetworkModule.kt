package com.hacka.presenteperfeito.core.network.di

import com.hacka.presenteperfeito.BuildConfig
import com.hacka.presenteperfeito.core.auth.data.repository.AuthRepository
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.LocalPreferencesRepository
import com.hacka.presenteperfeito.core.network.interceptor.SessionAuthenticator
import com.hacka.presenteperfeito.core.network.interceptor.SessionInterceptor
import com.hacka.presenteperfeito.feature.home.presentation.screen.components.ProgressStatusEnumAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.addAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 60L
const val ANONYMOUS_RETROFIT = "ANONYMOUS_RETROFIT"
const val ANONYMOUS_OKHTTP = "ANONYMOUS_OKHTTP"

@Single
@Named(ANONYMOUS_OKHTTP)
fun okhttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addNetworkInterceptor(loggingInterceptor)
    }
    return builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS).build()
}

@Single
fun authenticatedOkHttpClient(
    localPreferencesRepository: LocalPreferencesRepository, authRepository: AuthRepository
): OkHttpClient {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addNetworkInterceptor(loggingInterceptor)
    }
    return builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .authenticator(SessionAuthenticator(authRepository = authRepository))
        .addInterceptor(SessionInterceptor(localPreferencesRepository)).build()
}

@Single
fun moshi(): Moshi =
    Moshi.Builder().add(KotlinJsonAdapterFactory()).add(ProgressStatusEnumAdapter()).build()

@Single
@Named(ANONYMOUS_RETROFIT)
fun anonymousRetrofit(@Named(ANONYMOUS_OKHTTP) okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
    Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

@Single
fun authenticatedRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
    Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(
        okHttpClient
    ).addConverterFactory(MoshiConverterFactory.create(moshi)).build()