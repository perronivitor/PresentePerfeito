package com.hacka.presenteperfeito.core.common

import com.hacka.presenteperfeito.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single<OkHttpClient> {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("accept", "application/json")
                .header(
                    "Authorization",
                    "Bearer ${BuildConfig.API_TOKEN}"
                )
                .method(original.method, original.body)
            chain.proceed(requestBuilder.build())
        }

        builder
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ChatApiService> {
        get<Retrofit>().create(ChatApiService::class.java)
    }
}