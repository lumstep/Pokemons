package pokemonDetails.presentation.mvi

sealed interface PokemonDetailsEffects {

    object NavigateBack : PokemonDetailsEffects
}