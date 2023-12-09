package core.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import pokemonDetails.di.pokemonDetailsModules
import pokemonList.di.pokemonListModules

fun initKoin(init: KoinApplication.() -> Unit = {}) {
    startKoin {
        init()
        modules(htpClientModule, databaseModule)
        pokemonListModules
        pokemonDetailsModules
    }
}