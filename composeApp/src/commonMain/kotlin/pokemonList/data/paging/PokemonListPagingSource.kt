package pokemonList.data.paging

import app.cash.paging.*
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

    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, PokemonItemModel> {
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
                    return@withContext PagingSourceLoadResultError<Int, PokemonItemModel>(NullPointerException()) as PagingSourceLoadResult<Int, PokemonItemModel>
                }

                val lastId = pokemonsQueries.getLastID().awaitAsOne().lastIndex
                val prevKey = (nextPageNumber - 1).takeIf { nextPageNumber != 0 }
                val nextKey = (nextPageNumber + 1).takeIf { lastId != null && pokemons.last().id < lastId }

                Napier.e { "lastId - $lastId" }
                Napier.e { "prevKey - $prevKey" }
                Napier.e { "nextKey - $nextKey" }

                PagingSourceLoadResultPage(
                    data = pokemons,
                    prevKey = prevKey,
                    nextKey = nextKey,
                ) as PagingSourceLoadResult<Int, PokemonItemModel>
            } catch (exception: Exception) {
                Napier.e(throwable = exception, message = "exception when loading from database")
                PagingSourceLoadResultError<Int, PokemonItemModel>(exception) as PagingSourceLoadResult<Int, PokemonItemModel>
            }
        }
    }
}