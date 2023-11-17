package pokemonDetails.presentation.mvi

sealed interface PokemonDetailsEvents {

    object OnBackPressed : PokemonDetailsEvents
    object OnHomeTypePressed : PokemonDetailsEvents
    object OnArtWorkTypePressed : PokemonDetailsEvents
    object OnNextPressed : PokemonDetailsEvents
    object OnPreviousPressed : PokemonDetailsEvents
}
