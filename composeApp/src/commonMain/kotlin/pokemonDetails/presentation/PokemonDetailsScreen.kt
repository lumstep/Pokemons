package pokemonDetails.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pokemonDetails.presentation.mvi.PokemonDetailsEvents
import pokemonDetails.presentation.mvi.PokemonDetailsState

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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            PokemonImageBig(
                url = state.avatar,
                shinyUrl = state.shinyAvatar,
            )

            PokemonName(
                modifier = Modifier.offset(y = (-20).dp),
                name = state.name,
            )

            PokemoneAvatarSettings(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp)
                    .padding(horizontal = 20.dp),
                onArtClick = { onEvent(PokemonDetailsEvents.OnArtWorkTypePressed) },
                onHomeClick = { onEvent(PokemonDetailsEvents.OnHomeTypePressed) },
            )

            Spacer(modifier = Modifier.weight(1f))

            PokemonCharacteristics(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 16.dp),
                weight = state.weight,
                height = state.height,
                experience = state.experience,
            )
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

        PokemonType(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 120.dp),
            type = state.type,
        )
    }
}
