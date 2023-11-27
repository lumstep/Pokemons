package pokemonList.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pokemonList.domain.PokemonItemModel

val localCache = mutableMapOf<Int, List<PokemonItemModel>>()

//TODO The local database should be here
class PokemonListPagingSource : PagingSource<Int, PokemonItemModel>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonItemModel>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItemModel> {
        val nextPageNumber = params.key ?: 0
        return localCache[nextPageNumber]?.let { cache ->
            LoadResult.Page(
                data = cache,
                prevKey = (nextPageNumber - 1).takeIf { localCache.containsKey(nextPageNumber - 1) },
                nextKey = (nextPageNumber + 1).takeIf { localCache.containsKey(nextPageNumber + 1) },
            )
        } ?: LoadResult.Error(Throwable())
    }
}