package com.hacka.presenteperfeito

import android.app.Application
import com.hacka.presenteperfeito.core.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class PerfectGiftApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PerfectGiftApp)
            modules(AppModule().module)
        }
    }
}