package pokemonList.data.paging

import androidx.paging.*
import core.domain.Resource
import core.domain.tryGetData
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import pokemonDetails.data.api.PokemonInfoApi
import pokemonList.data.api.PokemonListApi
import pokemonList.domain.PokemonItemModel

private const val START_PAGE = 0

@OptIn(ExperimentalPagingApi::class)
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
    ): MediatorResult {

        val loadKey = when (loadType) {
            LoadType.REFRESH -> {
                Napier.e { "LoadType.REFRESH" }
                START_PAGE
            }

            LoadType.PREPEND -> {
                Napier.e { "LoadType.PREPEND" }

                return MediatorResult.Success(
                    endOfPaginationReached = false
                )
            }

            LoadType.APPEND -> {
                Napier.e { "LoadType.APPEND" }

                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                ).also {
                    Napier.e { "LoadType.APPEND MediatorResult.Success endOfPaginationReached = true" }
                }
                lastItem.id / state.config.pageSize
            }
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

                MediatorResult.Success(
                    endOfPaginationReached = resource.data.next.isNullOrEmpty(),
                )
            }

            is Resource.Error -> {
                Napier.e(
                    message = "error when loading from network",
                    throwable = resource.exception,
                )
                MediatorResult.Error(resource.exception)
            }
        }
    }

    private fun getUrlForPage(page: Int, limit: Int): String =
        "https://pokeapi.co/api/v2/pokemon/?offset=${page * limit}&limit=$limit"
}