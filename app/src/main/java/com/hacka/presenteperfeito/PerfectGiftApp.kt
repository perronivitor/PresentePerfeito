package com.hacka.presenteperfeito

import android.app.Application
import com.hacka.presenteperfeito.core.common.network.di.networkModule
import org.koin.core.context.GlobalContext.startKoin

class PerfectGiftApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule)
        }
    }
}