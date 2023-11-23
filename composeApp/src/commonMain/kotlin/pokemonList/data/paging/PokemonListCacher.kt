package pokemonList.data.paging

import pokemonDetails.data.dto.PokemonInfoDTO

interface PokemonListCacher {

    suspend fun cachePokemons(page: Int, pokemons: List<PokemonInfoDTO>)
}