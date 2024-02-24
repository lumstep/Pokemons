package pokemonList.data.paging

import app.cash.paging.*
import core.domain.Resource
import core.domain.tryGetData
import core.paging.InvalidatingPagingSourceFactory
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import pokemonDetails.data.api.PokemonInfoApi
import pokemonList.data.api.PokemonListApi
import pokemonList.domain.PokemonItemModel

private const val START_PAGE = 0

@ExperimentalPagingApi
class PokemonListRemoteMediator(
    private val pokemonListApi: PokemonListApi,
    private val pokemonInfoApi: PokemonInfoApi,
    private val pokemonListCacher: PokemonListCacher,
    private val invalidatingPagingSourceFactory: InvalidatingPagingSourceFactory<Int, PokemonItemModel>,
    private val dispatcher: CoroutineDispatcher,
) : RemoteMediator<Int, PokemonItemModel>() {

    private fun invalidate() {
        Napier.e { "invalidate" }
        invalidatingPagingSourceFactory.invalidate()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonItemModel>,
    ): RemoteMediatorMediatorResult {

        val loadKey = when (loadType) {
            LoadType.REFRESH -> {
                Napier.e { "LoadType.REFRESH" }
                START_PAGE
            }

            LoadType.PREPEND -> {
                Napier.e { "LoadType.PREPEND" }

                return RemoteMediatorMediatorResultSuccess(
                    endOfPaginationReached = false
                ) as RemoteMediatorMediatorResult
            }

            LoadType.APPEND -> {
                Napier.e { "LoadType.APPEND" }

                val lastItem = state.lastItemOrNull() ?: return (RemoteMediatorMediatorResultSuccess(
                    endOfPaginationReached = true
                ) as RemoteMediatorMediatorResult).also {
                    Napier.e { "LoadType.APPEND MediatorResult.Success endOfPaginationReached = true" }
                }
                lastItem.id / state.config.pageSize
            }

            else -> TODO()
        }

        Napier.e { "loadKey $loadKey" }

        val resource = withContext(dispatcher) {
            tryGetData {
                pokemonListApi.getPokemonList(
                    getUrlForPage(
                        page = loadKey,
                        limit = state.config.pageSize,
                    )
                )
            }
        }

        return when (resource) {
            is Resource.Success -> {
                val pokemons = resource.data.results
                    ?.map {
                        withContext(dispatcher) {
                            async {
                                val pokemonInfoResource = tryGetData {
                                    pokemonInfoApi.getPokemonInfo(it?.url.orEmpty())
                                }
                                (pokemonInfoResource as? Resource.Success)?.data
                            }
                        }
                    }
                    ?.awaitAll()
                    ?.filterNotNull() ?: listOf()

                Napier.e { "Load from network size - ${pokemons.size}, first id - ${pokemons.firstOrNull()?.id}" }

                pokemonListCacher.cachePokemons(page = loadKey, pokemons = pokemons)
                invalidate()

                Napier.e { "MediatorResult.Success ${resource.data.next.isNullOrEmpty()}" }

                RemoteMediatorMediatorResultSuccess(
                    endOfPaginationReached = resource.data.next.isNullOrEmpty(),
                ) as RemoteMediatorMediatorResult
            }

            is Resource.Error -> {
                Napier.e(
                    message = "error when loading from network",
                    throwable = resource.exception,
                )
                RemoteMediatorMediatorResultError(resource.exception) as RemoteMediatorMediatorResult
            }
        }
    }

    private fun getUrlForPage(page: Int, limit: Int): String =
        "https://pokeapi.co/api/v2/pokemon/?offset=${page * limit}&limit=$limit"
}