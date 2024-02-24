@file:OptIn(ExperimentalPagingApi::class)

package pokemonList.di

import app.cash.paging.*
import core.paging.InvalidatingPagingSourceFactory
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.dsl.bind
import org.koin.dsl.module
import pokemonDetails.data.api.PokemonInfoApi
import pokemonDetails.data.api.PokemonInfoApiImpl
import pokemonDetails.data.repository.PokemonRepositoryImpl
import pokemonDetails.domain.PokemonRepository
import pokemonList.data.api.PokemonListApi
import pokemonList.data.api.PokemonListApiImpl
import pokemonList.data.paging.PokemonListCacher
import pokemonList.data.paging.PokemonListCacherImpl
import pokemonList.data.paging.PokemonListPagingSource
import pokemonList.data.paging.PokemonListRemoteMediator
import pokemonList.domain.PokemonItemModel
import pokemonList.presentation.PokemonListViewModel

private const val PAGE_SIZE = 20

val KoinApplication.pokemonListModules: KoinApplication
    get() = modules(dataModule, domainModule, presentationModule)

@ExperimentalPagingApi
private val dataModule = module {
    scope<PokemonListScope> {
        scoped { PokemonRepositoryImpl(get(), get(), Dispatchers.Default) } bind PokemonRepository::class
        scoped { PokemonInfoApiImpl(get()) } bind PokemonInfoApi::class
        scoped { PokemonListApiImpl(get()) } bind PokemonListApi::class
        scoped {
            InvalidatingPagingSourceFactory {
                PokemonListPagingSource(get(), PAGE_SIZE, Dispatchers.Default)
            }
        } bind InvalidatingPagingSourceFactory::class
        scoped {
            PokemonListCacherImpl(get(), Dispatchers.Default)
        } bind PokemonListCacher::class

        scoped { PokemonListRemoteMediator(get(), get(), get(), get(), Dispatchers.Default) } bind RemoteMediator::class
    }
}

private val domainModule = module {
    scope<PokemonListScope> {
    }
}

@ExperimentalPagingApi
private val presentationModule = module {
    scope<PokemonListScope> {
        scoped {
            PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE * 3,
                enablePlaceholders = true,
                maxSize = PAGE_SIZE * 5,
                jumpThreshold = COUNT_UNDEFINED,
                prefetchDistance = PAGE_SIZE * 2,
            )
        }
        scoped {
            val factory = get<InvalidatingPagingSourceFactory<Int, PokemonItemModel>>()
            createPager(
                config = get(),
                initialKey = null,
                pagingSourceFactory = { factory() },
                remoteMediator = get<RemoteMediator<Int, PokemonItemModel>>(),
            )
        }
        scoped { PokemonListViewModel(get()) }
    }
}