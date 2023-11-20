package pokemonList.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import pokemonList.data.api.PokemonListApi
import pokemonList.domain.PokemonItemModel

private const val START_PAGE = "https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20"

@OptIn(ExperimentalPagingApi::class)
class PokemonListRemoteMediator(
    private val pokemonListApi: PokemonListApi,
) : RemoteMediator<Int, PokemonItemModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonItemModel>,
    ): MediatorResult {
      /*  val loadKey = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(
                endOfPaginationReached = true
            )

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    1
                } else {
                    (lastItem.id / state.config.pageSize) + 1
                }
            }
        }*/

        pokemonListApi.getPokemonList(START_PAGE)

        return MediatorResult.Success(
            endOfPaginationReached = true,
        )
    }
}