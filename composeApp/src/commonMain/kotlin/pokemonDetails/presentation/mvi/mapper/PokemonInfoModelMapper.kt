package pokemonDetails.presentation.mvi.mapper

import core.util.capitalize
import pokemonDetails.domain.PokemonInfoModel
import pokemonDetails.presentation.mvi.PokemonDetailsState

fun PokemonInfoModel.toPokemonDetailsState(
    avatarType: PokemonDetailsState.AvatarTypes,
) = PokemonDetailsState(
    name = name.capitalize(),
    type = type?.capitalize(),
    avatar = when (avatarType) {
        PokemonDetailsState.AvatarTypes.Home -> homeAvatar.orEmpty()
        PokemonDetailsState.AvatarTypes.Art -> artWorkAvatar.orEmpty()
    },
    shinyAvatar = when (avatarType) {
        PokemonDetailsState.AvatarTypes.Home -> homeShinyAvatar.orEmpty()
        PokemonDetailsState.AvatarTypes.Art -> artWorkShinyAvatar.orEmpty()
    },
    weight = weight?.toString() ?: "N/A", // TODO to resources
    height = height?.toString() ?: "N/A",
    experience = experience?.toString() ?: "N/A",
)