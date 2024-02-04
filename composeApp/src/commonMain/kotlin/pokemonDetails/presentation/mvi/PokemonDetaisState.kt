package pokemonDetails.presentation.mvi

data class PokemonDetailsState(
    val isLoading: Boolean,
    val avatar: String,
    val shinyAvatar: String,
    val selectedAvatarType: AvatarTypes,
    val name: String,
    val type: String?,
    val weight: String,
    val height: String,
    val experience: String,
) {
    enum class AvatarTypes {
        HOME,
        ART,
    }
}
