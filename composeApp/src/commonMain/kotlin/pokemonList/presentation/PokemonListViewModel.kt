package pokemonList.presentation

import androidx.paging.Pager
import androidx.paging.PagingData
import com.hoc081098.kmp.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pokemonList.domain.PokemonItemModel
import pokemonList.presentation.mvi.PokemonListEffects
import pokemonList.presentation.mvi.PokemonListEvents

class PokemonListViewModel(
    private val pager: Pager<Int, PokemonItemModel>,
) : ViewModel() {

    private val _effects: Channel<PokemonListEffects> = Channel()
    val effects = _effects.receiveAsFlow()

    fun getPokemons(): Flow<PagingData<PokemonItemModel>> = pager.flow

    fun handleEvent(event: PokemonListEvents) {
        viewModelScope.launch {
            when (event) {
                is PokemonListEvents.OnPokemonClick -> _effects.send(
                    PokemonListEffects.NavigateToPokemonDetails(
                        pokemonId = event.pokemonId
                    )
                )
            }
        }
    }
}