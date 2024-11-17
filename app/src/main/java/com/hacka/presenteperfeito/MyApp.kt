package com.hacka.presenteperfeito

import android.app.Application
import com.hacka.presenteperfeito.core.common.networkModule
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule)
        }
    }
}