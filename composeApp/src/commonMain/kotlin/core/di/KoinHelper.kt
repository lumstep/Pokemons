package core.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module._scopedInstanceFactory

fun initKoin(init: KoinApplication.() -> Unit = {}) {
    startKoin {
        init()
        modules{
            _scopedInstanceFactory()
        }
    }
}