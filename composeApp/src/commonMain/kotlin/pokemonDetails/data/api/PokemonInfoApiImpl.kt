package pokemonDetails.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import pokemonDetails.data.dto.PokemonInfoDTO

class PokemonInfoApiImpl(
    private val httpClient: HttpClient,
) : PokemonInfoApi {
    override suspend fun getPokemonInfo(id: Int): PokemonInfoDTO =
        httpClient.get("https://pokeapi.co/api/v2/pokemon/$id/").body()

    override suspend fun getPokemonInfo(url: String): PokemonInfoDTO = httpClient.get(url).body()
}