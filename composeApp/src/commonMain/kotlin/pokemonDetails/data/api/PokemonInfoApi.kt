package pokemonDetails.data.api

import pokemonDetails.data.dto.PokemonInfoDTO

interface PokemonInfoApi {

    suspend fun getPokemonInfo(id: Int): PokemonInfoDTO
    suspend fun getPokemonInfo(url: String): PokemonInfoDTO
}