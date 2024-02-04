package pokemonList.presentation.mvi

sealed interface PokemonListEffects {

    class NavigateToPokemonDetails(val pokemonId: Int) : PokemonListEffects
}
