package pokemonDetails.data.repository

import app.cash.sqldelight.async.coroutines.awaitAsOne
import core.domain.Resource
import core.domain.tryGetData
import core.domain.tryIfError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.lumstep.PokemonsQueries
import pokemonDetails.data.api.PokemonInfoApi
import pokemonDetails.data.mapper.toPokemonDetailInfoModel
import pokemonDetails.domain.PokemonDetailInfoModel
import pokemonDetails.domain.PokemonRepository

class PokemonRepositoryImpl(
    private val pokemonInfoApi: PokemonInfoApi,
    private val pokemonsQueries: PokemonsQueries,
    private val dispatcher: CoroutineDispatcher,
) : PokemonRepository {

    override suspend fun getPokemonInfo(
        id: Int,
        forceUpdate: Boolean,
    ): Resource<PokemonDetailInfoModel> = withContext(dispatcher) {
        if (forceUpdate) {
            tryGetData {
                pokemonInfoApi.getPokemonInfo(id).toPokemonDetailInfoModel()
            }
        } else {
            tryGetData {
                pokemonsQueries.getPokemon(id.toLong()).awaitAsOne().toPokemonDetailInfoModel()
            }.tryIfError {
                pokemonInfoApi.getPokemonInfo(id).toPokemonDetailInfoModel()
            }
        }
    }
}