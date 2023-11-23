package pokemonDetails.data.mapper

import pokemonDetails.data.dto.PokemonInfoDTO
import pokemonDetails.domain.PokemonDetailInfoModel
import pokemonList.domain.PokemonItemModel

fun PokemonInfoDTO.toPokemonDetailInfoModel() = PokemonDetailInfoModel(
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

fun PokemonInfoDTO.toPokemonSmallItemModel() = PokemonItemModel(
    id = id,
    name = name.orEmpty(),
    imageUrl = sprites?.other?.home?.frontDefault.orEmpty(),
)