package pokemonDetails.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
    Box(
        modifier = modifier
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
                    modifier = Modifier.onSizeChanged {
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
                    },
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
                        onArtClick = { onEvent(PokemonDetailsEvents.OnArtWorkTypePressed) },
                        onHomeClick = { onEvent(PokemonDetailsEvents.OnHomeTypePressed) },
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    PokemonType(
                        modifier = Modifier.padding(horizontal = 30.dp),
                        type = state.type,
                    )

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


        NavigateNextButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 40.dp),
            onClick = {
                onEvent(PokemonDetailsEvents.OnNextPressed)
            },
        )

        NavigatePreviousButton(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 40.dp),
            onClick = {
                onEvent(PokemonDetailsEvents.OnNextPressed)
            },
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
}
