package org.apptrick.invotick.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}

val sharedModule = module {
//    viewModelOf(::InvoiceViewModel)
}

expect val platformModule: Module