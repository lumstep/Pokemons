package pokemonDetails.data.repository

import core.domain.Resource
import core.domain.tryGetData
import pokemonDetails.data.api.PokemonInfoApi
import pokemonDetails.data.mapper.toPokemonDetailInfoModel
import pokemonDetails.domain.PokemonDetailInfoModel
import pokemonDetails.domain.PokemonRepository

class PokemonRepositoryImpl (
    private val pokemonInfoApi: PokemonInfoApi,
): PokemonRepository {

    override suspend fun getPokemonInfo(id: Int, forceUpdate: Boolean): Resource<PokemonDetailInfoModel> =
        tryGetData {
            pokemonInfoApi.getPokemonInfo(id).toPokemonDetailInfoModel()
        }
}