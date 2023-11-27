package pokemonList.presentation

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pokemonList.domain.PokemonItemModel

class PokemonListViewModel(
    private val pager: Pager<Int, PokemonItemModel>,
) {

    fun getPokemons(): Flow<PagingData<PokemonItemModel>> = pager.flow
}