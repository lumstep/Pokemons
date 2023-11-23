package pokemonDetails.domain

data class PokemonDetailInfoModel(
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
