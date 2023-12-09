package pokemonList.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.InvalidatingPagingSourceFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
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

@OptIn(ExperimentalPagingApi::class)
private val dataModule = module {
    scope<PokemonListScope> {
        scoped { PokemonRepositoryImpl(get(), get(), Dispatchers.IO) } bind PokemonRepository::class
        scoped { PokemonInfoApiImpl(get()) } bind PokemonInfoApi::class
        scoped { PokemonListApiImpl(get()) } bind PokemonListApi::class
        scoped {
            InvalidatingPagingSourceFactory {
                PokemonListPagingSource(get(), PAGE_SIZE, Dispatchers.IO)
            }
        } bind InvalidatingPagingSourceFactory::class
        scoped {
            PokemonListCacherImpl(get(), Dispatchers.IO)
        } bind PokemonListCacher::class

        scoped { PokemonListRemoteMediator(get(), get(), get(), get(), Dispatchers.IO) } bind RemoteMediator::class
    }
}

private val domainModule = module {
    scope<PokemonListScope> {
    }
}

@OptIn(ExperimentalPagingApi::class)
private val presentationModule = module {
    scope<PokemonListScope> {
        scoped {
            PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE * 3,
                enablePlaceholders = true,
                maxSize = PAGE_SIZE * 5,
            )
        }
        scoped {
            Pager(
                config = get(),
                pagingSourceFactory = get<InvalidatingPagingSourceFactory<Int, PokemonItemModel>>(),
                remoteMediator = get<RemoteMediator<Int, PokemonItemModel>>(),
            )
        }
        scoped { PokemonListViewModel(get()) }
    }
}