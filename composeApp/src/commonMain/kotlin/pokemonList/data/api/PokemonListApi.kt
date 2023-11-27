package pokemonList.data.api

import pokemonList.data.dto.PokemonListPageDTO

interface PokemonListApi {

    suspend fun getPokemonList(url: String): PokemonListPageDTO

}