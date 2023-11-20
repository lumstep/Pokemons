package pokemonDetails.data.mapper

import pokemonDetails.data.dto.PokemonInfoDTO
import pokemonDetails.domain.PokemonInfoModel

fun PokemonInfoDTO.toPokemonInfoModel() = PokemonInfoModel(
    id = id,
    homeAvatar = sprites?.other?.home?.frontDefault,
    homeShinyAvatar = sprites?.other?.home?.frontShiny,
    artWorkAvatar = sprites?.other?.officialArtwork?.frontDefault,
    artWorkShinyAvatar = sprites?.other?.officialArtwork?.frontShiny,
    name = name.orEmpty(),
    type = types?.firstOrNull()?.type?.name,
    weight = weight,
    height = height,
    experience = baseExperience,
)