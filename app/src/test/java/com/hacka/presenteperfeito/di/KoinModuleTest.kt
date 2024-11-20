package com.hacka.presenteperfeito.di

import com.hacka.presenteperfeito.PerfectGiftApp
import com.hacka.presenteperfeito.core.common.dispatcher.DispatcherIO
import com.hacka.presenteperfeito.core.common.dispatcher.DispatcherMain
import com.hacka.presenteperfeito.core.common.dispatcher.GiftPerfectDispatchers
import com.hacka.presenteperfeito.core.di.AppModule
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.ksp.generated.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.verify.verifyAll
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class KoinModuleTest: KoinTest {

    @Before
    fun startKoinForTest(){
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(PerfectGiftApp())
                modules(AppModule().module)
            }
        }
    }

    @OptIn(KoinInternalApi::class, KoinExperimentalAPI::class)
    @Test
    fun `check dependence injection`() {
        AppModule().module.includedModules.verifyAll()
    }

    @Test
    fun `check dispatcher injection`() {
        val dispatcherIo: GiftPerfectDispatchers by inject(named(DispatcherIO))
        val dispatcherMain: GiftPerfectDispatchers by inject(named(DispatcherMain))
        assertEquals(dispatcherIo(), Dispatchers.IO)
        assertEquals(dispatcherMain(), Dispatchers.Main)
        assertNotEquals(dispatcherMain(), Dispatchers.IO)
        assertNotEquals(dispatcherIo(), Dispatchers.Main)
    }
}