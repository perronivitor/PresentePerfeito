package com.hacka.presenteperfeito.core.common.extensions

import androidx.compose.runtime.Composable
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import com.hacka.presenteperfeito.core.di.AppModule
import org.koin.compose.KoinApplication
import org.koin.ksp.generated.module

@Composable
fun KoinPreview(content: @Composable () -> Unit) {
    KoinApplication(application = {
        modules(AppModule().module)
    }) {
        PerfectGiftTheme {
            content()
        }
    }
}