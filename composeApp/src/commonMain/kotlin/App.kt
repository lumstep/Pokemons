import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.ExperimentalPagingApi
import core.configs.HttpClientKeeper
import core.configs.InitialConfigsProvider
import core.theme.PokemonTheme
import pokemonDetails.data.api.PokemonInfoApiImpl
import pokemonDetails.data.repository.PokemonRepositoryImpl
import pokemonDetails.presentation.PokemonDetailsScreen
import pokemonDetails.presentation.mvi.PokemonDetailsViewModel
import pokemonList.presentation.PokemonListScreen

@OptIn(ExperimentalPagingApi::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    PokemonTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        InitialConfigsProvider {
             PokemonListScreen()

/*
            val viewModel = remember {
                PokemonDetailsViewModel(
                    PokemonRepositoryImpl(
                        PokemonInfoApiImpl(
                            HttpClientKeeper.httpClient
                        )
                    )
                )
            }
            val state by viewModel.state.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.initPokemon(1)
            }

            state?.let {
                PokemonDetailsScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    onEvent = viewModel::handleEvent,
                    state = it,
                )
            }*/
        }
    }
}
