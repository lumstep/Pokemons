package pokemonList.data.paging

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOne
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.lumstep.PokemonsQueries
import pokemonDetails.data.mapper.toPokemonItemModel
import pokemonList.domain.PokemonItemModel

class PokemonListPagingSource(
    private val pokemonsQueries: PokemonsQueries,
    private val pageSize: Int,
    private val dispatcher: CoroutineDispatcher,
) : PagingSource<Int, PokemonItemModel>() {

    init {
        Napier.e { "Init new paging source" }
        registerInvalidatedCallback {
            Napier.e { "invalidate paging source" }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonItemModel>): Int? {
        Napier.e { " get refresh key - $state" }
        val lastPage = state.pages.lastOrNull()
        val key = lastPage?.nextKey ?: lastPage?.prevKey?.let { it + 2 } ?: 0
        return key.also {
            Napier.e { "refresh key - $it" }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItemModel> {
        val nextPageNumber = params.key ?: 0
        Napier.e { "nextPageNumber - $nextPageNumber" }

        return withContext(dispatcher) {
            try {
                val pokemons = pokemonsQueries.getPokemons(
                    (pageSize * nextPageNumber).toLong(),
                    pageSize.toLong()
                ).awaitAsList().map { it.toPokemonItemModel() }

                if (pokemons.isEmpty()) {
                    Napier.e { "pokemon list is empty" }
                    return@withContext LoadResult.Error(NullPointerException())
                }

                val lastId = pokemonsQueries.getLastID().awaitAsOne().lastIndex
                val prevKey = (nextPageNumber - 1).takeIf { nextPageNumber != 0 }
                val nextKey = (nextPageNumber + 1).takeIf { lastId != null && pokemons.last().id < lastId }

                Napier.e { "lastId - $lastId" }
                Napier.e { "prevKey - $prevKey" }
                Napier.e { "nextKey - $nextKey" }

                LoadResult.Page(
                    data = pokemons,
                    prevKey = prevKey,
                    nextKey = nextKey,
                )
            } catch (exception: Exception) {
                Napier.e(throwable = exception, message = "exception when loading from database")
                LoadResult.Error(exception)
            }
        }
    }
}