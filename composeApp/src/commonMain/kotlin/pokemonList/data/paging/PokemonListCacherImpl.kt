package pokemonList.data.paging

import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.lumstep.PokemonsQueries
import pokemonDetails.data.dto.PokemonInfoDTO

class PokemonListCacherImpl(
    private val pokemonsQueries: PokemonsQueries,
    private val dispatcher: CoroutineDispatcher,
) : PokemonListCacher {
    override suspend fun cachePokemons(page: Int, pokemons: List<PokemonInfoDTO>) {
        withContext(dispatcher) {
            Napier.d { "cachePokemons page - $page, size - ${pokemons.size}, first id - ${pokemons.firstOrNull()?.id}" }
            pokemons.map { pokemonDTO ->
                launch {
                    with(pokemonDTO) {
                        pokemonsQueries.insertPokemon(
                            id = id.toLong(),
                            name = name.orEmpty(),
                            type = types?.firstOrNull()?.type?.name,
                            weight = weight?.toLong(),
                            height = height?.toLong(),
                            experience = baseExperience?.toLong(),
                            home_avatar = sprites?.other?.home?.frontDefault,
                            home_shiny_avatar = sprites?.other?.home?.frontShiny,
                            art_work_avatar = sprites?.other?.officialArtwork?.frontDefault,
                            art_work_shiny_avatar = sprites?.other?.officialArtwork?.frontShiny,
                        )
                    }
                }
            }.joinAll()
        }
    }
}