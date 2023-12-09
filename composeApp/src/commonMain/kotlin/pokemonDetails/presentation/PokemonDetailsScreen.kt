package pokemonDetails.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import core.configs.screenSizeProvider
import pokemonDetails.presentation.mvi.PokemonDetailsEvents
import pokemonDetails.presentation.mvi.PokemonDetailsState
import pokemonDetails.presentation.mvi.PokemonDetailsState.AvatarTypes

private sealed interface PokemonDisplayingInfo {

    object Bottom : PokemonDisplayingInfo
    object Center : PokemonDisplayingInfo
    object End : PokemonDisplayingInfo
}


@Composable
fun PokemonDetailsScreen(
    modifier: Modifier = Modifier,
    state: PokemonDetailsState,
    onEvent: (PokemonDetailsEvents) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
        ) {
            var pokemonDisplayingInfo: PokemonDisplayingInfo by remember {
                mutableStateOf(PokemonDisplayingInfo.Bottom)
            }

            val screenSize = screenSizeProvider()

            val pokemonInfo = remember(screenSize, state.avatar, state.shinyAvatar, state.name) {
                movableContentOf {
                    PokemonImageBig(
                        modifier = Modifier
                            .onSizeChanged {
                                val heightDiff = screenSize.height - it.height
                                if (heightDiff > 300) {
                                    pokemonDisplayingInfo = PokemonDisplayingInfo.Bottom
                                    return@onSizeChanged
                                }
                                val widthDiff = screenSize.width - it.width
                                if (widthDiff > 300) {
                                    pokemonDisplayingInfo = PokemonDisplayingInfo.End
                                    return@onSizeChanged
                                }
                                pokemonDisplayingInfo = PokemonDisplayingInfo.Center
                            }
                            .padding(horizontal = if (pokemonDisplayingInfo is PokemonDisplayingInfo.End) 50.dp else 0.dp),
                        url = state.avatar,
                        shinyUrl = state.shinyAvatar,
                    )

                    Column(
                        modifier = Modifier
                            .padding(top = if (pokemonDisplayingInfo is PokemonDisplayingInfo.End) 40.dp else 0.dp)
                            .align(if (pokemonDisplayingInfo is PokemonDisplayingInfo.End) Alignment.CenterEnd else Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        PokemonName(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            name = state.name,
                        )

                        PokemoneAvatarSettings(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            onClick = { type ->
                                when (type) {
                                    AvatarTypes.HOME -> onEvent(PokemonDetailsEvents.OnHomeTypePressed)
                                    AvatarTypes.ART -> onEvent(PokemonDetailsEvents.OnArtWorkTypePressed)
                                }
                            },
                            selectedType = state.selectedAvatarType,
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        state.type?.let {
                            PokemonType(
                                modifier = Modifier.padding(horizontal = 30.dp),
                                type = it,
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        PokemonCharacteristics(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .padding(bottom = 16.dp),
                            weight = state.weight,
                            height = state.height,
                            experience = state.experience,
                        )
                    }
                }
            }

            when (pokemonDisplayingInfo) {
                PokemonDisplayingInfo.Bottom -> Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    pokemonInfo()
                }

                else -> {
                    pokemonInfo()
                }
            }

            NavigateButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 40.dp),
                onClick = {
                    onEvent(PokemonDetailsEvents.OnNextPressed)
                },
                navigateButtonType = NavigateButtonType.Next,
            )

            NavigateButton(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 40.dp),
                onClick = {
                    onEvent(PokemonDetailsEvents.OnPreviousPressed)
                },
                navigateButtonType = NavigateButtonType.Previous,
            )

            BackButton(
                modifier = Modifier
                    .statusBarsPadding()
                    .align(Alignment.TopStart)
                    .padding(all = 16.dp),
                onClick = {
                    onEvent(PokemonDetailsEvents.OnBackPressed)
                },
            )
        }
        PokemonSparks(modifier = Modifier.aspectRatio(1f))
    }
}
