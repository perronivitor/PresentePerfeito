package com.hacka.presenteperfeito.core.network.di

import com.hacka.presenteperfeito.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 60L

@Single
fun okhttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addInterceptor(loggingInterceptor)
    }
    return builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS).build()
}

@Single
fun moshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

@Single
fun retrofit(): Retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okhttpClient())
    .addConverterFactory(MoshiConverterFactory.create(moshi())).build()