package pokemonDetails.presentation.mvi

data class PokemonDetailsState(
    val avatar: String,
    val shinyAvatar: String,
    val name: String,
    val type: String?,
    val weight: String,
    val height: String,
    val experience: String,
) {
    sealed interface AvatarTypes {
        object Home : AvatarTypes
        object Art : AvatarTypes
    }
}
