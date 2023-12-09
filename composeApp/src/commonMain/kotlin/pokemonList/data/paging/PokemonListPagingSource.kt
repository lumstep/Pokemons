package pokemonList.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
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
        Napier.d { "Init new paging source" }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonItemModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }.also {
            Napier.d { "refresh key - $it" }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItemModel> {
        val nextPageNumber = params.key ?: 0
        Napier.d { "nextPageNumber - $nextPageNumber" }

        return withContext(dispatcher) {
            try {
                val pokemons = pokemonsQueries.getPokemons(
                    (pageSize * nextPageNumber).toLong(),
                    pageSize.toLong()
                ).awaitAsList().map { it.toPokemonItemModel() }

                val lastId = pokemonsQueries.getLastID().awaitAsOne().lastIndex
                val prevKey = (nextPageNumber - 1).takeIf { nextPageNumber != 0 }
                val nextKey =
                    (nextPageNumber + 1).takeIf { lastId != null && pokemons.isNotEmpty() && pokemons.last().id < lastId }

                Napier.d { "lastId - $lastId" }
                Napier.d { "prevKey - $prevKey" }
                Napier.d { "nextKey - $nextKey" }

                LoadResult.Page(
                    data = pokemons,
                    prevKey = prevKey,
                    nextKey = nextKey,
                )
            } catch (exception: Exception) {
                Napier.d(throwable = exception, message = "exception when loading from database")
                LoadResult.Error(exception)
            }
        }
    }
}