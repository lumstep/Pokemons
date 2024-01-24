package pokemonList.presentation.mvi

sealed interface PokemonListEvents {

    class OnPokemonClick(val pokemonId: Int) : PokemonListEvents
}
