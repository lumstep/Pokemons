package pokemonDetails.domain

import core.domain.Resource

interface PokemonRepository {

    suspend fun getPokemonInfo(id: Int, forceUpdate: Boolean = false): Resource<PokemonInfoModel>
}