package pokemonList.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.InvalidatingPagingSourceFactory
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import core.domain.Resource
import core.domain.tryGetData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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
) : RemoteMediator<Int, PokemonItemModel>() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private fun invalidate() {
        invalidatingPagingSourceFactory.invalidate()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonItemModel>,
    ): MediatorResult {

        val loadKey = when (loadType) {
            LoadType.REFRESH -> START_PAGE
            LoadType.PREPEND -> return MediatorResult.Success(
                endOfPaginationReached = true
            )

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                lastItem.id / state.config.pageSize
            }
        }

        val resource = tryGetData {
            pokemonListApi.getPokemonList(
                getUrlForPage(
                    page = loadKey,
                    limit = state.config.pageSize,
                )
            )
        }

        return when (resource) {
            is Resource.Success -> {
                val pokemons = resource.data.results
                    ?.map {
                        coroutineScope.async {
                            val pokemonInfoResource = tryGetData {
                                pokemonInfoApi.getPokemonInfo(it?.url.orEmpty())
                            }
                            (pokemonInfoResource as? Resource.Success)?.data
                        }
                    }
                    ?.awaitAll()
                    ?.filterNotNull() ?: listOf()

                pokemonListCacher.cachePokemons(page = loadKey, pokemons = pokemons)
                invalidate()

                MediatorResult.Success(
                    endOfPaginationReached = resource.data.next.isNullOrEmpty(),
                )
            }

            is Resource.Error -> {
                MediatorResult.Error(resource.exception)
            }
        }
    }

    private fun getUrlForPage(page: Int, limit: Int): String =
        "https://pokeapi.co/api/v2/pokemon/?offset=${page * limit}&limit=$limit"
}