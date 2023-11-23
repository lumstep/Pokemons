package pokemonList.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import pokemonList.data.dto.PokemonListPageDTO

class PokemonListApiImpl(
    private val httpClient: HttpClient,
) : PokemonListApi {
    override suspend fun getPokemonList(url: String): PokemonListPageDTO =
        httpClient.get(url).body()
}