package pokemonDetails.presentation.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonDetailsViewModel {

    private val _state = MutableStateFlow(
        PokemonDetailsState(
            avatar = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/2.png",
            shinyAvatar = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/2.png",
            name = "Charizard",
            experience = "131",
            weight = "131",
            height = "131",
            type = "Grass",
        )
    )
    val state = _state.asStateFlow()

    fun handleEvent(event: PokemonDetailsEvents) {
        CoroutineScope(Dispatchers.Main.immediate).launch {
            when (event) {
                PokemonDetailsEvents.OnArtWorkTypePressed -> {
                    _state.emit(
                        _state.value.copy(
                            avatar = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png",
                            shinyAvatar = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/2.png",
                        )
                    )
                }

                PokemonDetailsEvents.OnBackPressed -> {}
                PokemonDetailsEvents.OnHomeTypePressed -> {
                    _state.emit(
                        _state.value.copy(
                            avatar = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/2.png",
                            shinyAvatar = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/2.png",
                        )
                    )
                }

                PokemonDetailsEvents.OnNextPressed -> {}
                PokemonDetailsEvents.OnPreviousPressed -> {}
            }
        }
    }
}