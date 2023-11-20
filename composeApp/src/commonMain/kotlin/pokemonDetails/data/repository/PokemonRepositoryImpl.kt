package pokemonDetails.data.repository

import core.domain.Resource
import core.domain.tryGetData
import pokemonDetails.data.api.PokemonInfoApi
import pokemonDetails.data.mapper.toPokemonInfoModel
import pokemonDetails.domain.PokemonInfoModel
import pokemonDetails.domain.PokemonRepository

class PokemonRepositoryImpl (
    private val pokemonInfoApi: PokemonInfoApi,
): PokemonRepository {

    override suspend fun getPokemonInfo(id: Int, forceUpdate: Boolean): Resource<PokemonInfoModel> =
        tryGetData {
            pokemonInfoApi.getPokemonInfo(id).toPokemonInfoModel()
        }
}