package pokemonDetails.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.KoinApplication
import org.koin.dsl.bind
import org.koin.dsl.module
import pokemonDetails.data.api.PokemonInfoApi
import pokemonDetails.data.api.PokemonInfoApiImpl
import pokemonDetails.data.repository.PokemonRepositoryImpl
import pokemonDetails.domain.PokemonRepository
import pokemonDetails.presentation.mvi.PokemonDetailsViewModel

val KoinApplication.pokemonDetailsModules: KoinApplication
    get() = modules(dataModule, domainModule, presentationModule)

private val dataModule = module {
    scope<PokemonDetailsScope> {
        scoped { PokemonRepositoryImpl(get(), get (), Dispatchers.IO) } bind PokemonRepository::class
        scoped { PokemonInfoApiImpl(get()) } bind PokemonInfoApi::class
    }
}

private val domainModule = module {
    scope<PokemonDetailsScope> {
    }
}

private val presentationModule = module {
    scope<PokemonDetailsScope> {
        scoped { PokemonDetailsViewModel(get()) }
    }
}