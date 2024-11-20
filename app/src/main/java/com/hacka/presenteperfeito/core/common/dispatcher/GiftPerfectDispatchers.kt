package com.hacka.presenteperfeito.core.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

const val DispatcherIO = "DispatcherIO"
const val DispatcherMain = "DispatcherMain"

interface GiftPerfectDispatchers {
    operator fun invoke(): CoroutineDispatcher
}

@Single
@Named(value = DispatcherIO)
class CoroutineDispatcherIO : GiftPerfectDispatchers {
    override operator fun invoke() = Dispatchers.IO
}

@Single
@Named(value = DispatcherMain)
class CoroutineDispatcherMain : GiftPerfectDispatchers {
    override operator fun invoke() = Dispatchers.Main
}