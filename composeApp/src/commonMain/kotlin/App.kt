import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import core.configs.InitialConfigsProvider
import core.theme.PokemonTheme
import pokemonDetails.presentation.PokemonDetailsScreen
import pokemonDetails.presentation.mvi.PokemonDetailsViewModel

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    PokemonTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        val viewModel = remember { PokemonDetailsViewModel() }
        val state by viewModel.state.collectAsState()
        InitialConfigsProvider {
            PokemonDetailsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                onEvent = viewModel::handleEvent,
                state = state,
            )
        }
    }
}
