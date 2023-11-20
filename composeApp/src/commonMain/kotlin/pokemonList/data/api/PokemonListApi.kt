package pokemonList.data.api

import pokemonDetails.data.dto.PokemonInfoDTO

interface PokemonListApi {

    suspend fun getPokemonList(url: String): PokemonInfoDTO

}