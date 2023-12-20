package pokemonDetails.presentation.mvi

sealed interface PokemonDetailsEffects {

    object NavigateBack : PokemonDetailsEffects
    class Error(val message: String, val retry: () -> Unit) : PokemonDetailsEffects
}