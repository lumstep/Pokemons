package pokemonDetails.presentation.mvi.mapper

import core.util.capitalize
import pokemonDetails.domain.PokemonDetailInfoModel
import pokemonDetails.presentation.mvi.PokemonDetailsState

fun PokemonDetailInfoModel.toPokemonDetailsState(
    avatarType: PokemonDetailsState.AvatarTypes,
) = PokemonDetailsState(
    name = name.capitalize(),
    type = type?.capitalize(),
    avatar = when (avatarType) {
        PokemonDetailsState.AvatarTypes.HOME -> homeAvatar.orEmpty()
        PokemonDetailsState.AvatarTypes.ART -> artWorkAvatar.orEmpty()
    },
    shinyAvatar = when (avatarType) {
        PokemonDetailsState.AvatarTypes.HOME -> homeShinyAvatar.orEmpty()
        PokemonDetailsState.AvatarTypes.ART -> artWorkShinyAvatar.orEmpty()
    },
    weight = weight?.toString() ?: "N/A", // TODO to resources
    height = height?.toString() ?: "N/A",
    experience = experience?.toString() ?: "N/A",
    selectedAvatarType = avatarType,
    isLoading = false,
)