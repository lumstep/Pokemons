package pokemonDetails.presentation.mvi.mapper

import pokemonDetails.domain.PokemonInfoModel
import pokemonDetails.presentation.mvi.PokemonDetailsState

fun PokemonInfoModel.toPokemonDetailsState(
    avatarType: PokemonDetailsState.AvatarTypes,
) = PokemonDetailsState(
    name = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
    type = type,
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