package pokemonList.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PokemonListPageDTO(
    @SerialName("count")
    val count: Int?, // 1292
    @SerialName("next")
    val next: String?, // https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20
    @SerialName("previous")
    val previous: String?, // null
    @SerialName("results")
    val results: List<Result?>?
) {
    @Serializable
    class Result(
        @SerialName("name")
        val name: String?, // bulbasaur
        @SerialName("url")
        val url: String? // https://pokeapi.co/api/v2/pokemon/1/
    )
}