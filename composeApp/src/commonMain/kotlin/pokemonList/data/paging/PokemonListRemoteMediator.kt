package pokemonList.data.paging

import androidx.paging.ExperimentalPagingApi
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

@OptIn(ExperimentalPagingApi::class)
class PokemonListRemoteMediator(
    private val pokemonListApi: PokemonListApi,
    private val pokemonInfoApi: PokemonInfoApi,
    private val pokemonListCacher: PokemonListCacher,
) : RemoteMediator<Int, PokemonItemModel>() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var listeners = arrayListOf<() -> Unit>()

    fun addListener(listener: () -> Unit) {
        listeners.add(listener)
    }

    private fun removeListener(listener: () -> Unit) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        ArrayList(listeners).forEach {
            it()
            removeListener(it)
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonItemModel>,
    ): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(
                endOfPaginationReached = true
            )

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()

                // You must explicitly check if the last item is null when
                // appending, since passing null to networkService is only
                // valid for initial load. If lastItem is null it means no
                // items were loaded after the initial REFRESH and there are
                // no more items to load.
                if (lastItem == null) {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                lastItem.id
            }
        }

        val resource = tryGetData {
            pokemonListApi.getPokemonList(
                getUrlForPage(
                    page = loadKey,
                    limit = 20, // TODO dont hardcore it
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
                notifyListeners()
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