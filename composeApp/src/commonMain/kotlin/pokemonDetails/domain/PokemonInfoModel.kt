package pokemonDetails.domain

data class PokemonInfoModel(
    val id: Int,
    val homeAvatar: String?,
    val homeShinyAvatar: String?,
    val artWorkAvatar: String?,
    val artWorkShinyAvatar: String?,
    val name: String,
    val type: String?,
    val weight: Int?,
    val height: Int?,
    val experience: Int?,
)
