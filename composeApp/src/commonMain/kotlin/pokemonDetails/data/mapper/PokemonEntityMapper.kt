package pokemonDetails.data.mapper

import org.lumstep.Pokemon
import pokemonDetails.domain.PokemonDetailInfoModel
import pokemonList.domain.PokemonItemModel

fun Pokemon.toPokemonDetailInfoModel() = PokemonDetailInfoModel(
    id = id.toInt(),
    homeAvatar = home_avatar,
    homeShinyAvatar = home_shiny_avatar,
    artWorkAvatar = art_work_avatar,
    artWorkShinyAvatar = art_work_shiny_avatar,
    name = name,
    type = type,
    weight = weight?.toInt(),
    height = height?.toInt(),
    experience = experience?.toInt(),
)

fun Pokemon.toPokemonItemModel() = PokemonItemModel(
    id = id.toInt(),
    name = name,
    imageUrl = home_avatar.orEmpty(),
)