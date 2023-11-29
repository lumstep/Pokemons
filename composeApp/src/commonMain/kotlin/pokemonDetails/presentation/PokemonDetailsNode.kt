package pokemonDetails.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import core.configs.HttpClientKeeper
import pokemonDetails.data.api.PokemonInfoApiImpl
import pokemonDetails.data.repository.PokemonRepositoryImpl
import pokemonDetails.presentation.mvi.PokemonDetailsViewModel

class PokemonDetailsNode(
    private val pokemonId: Int,
    buildContext: BuildContext,
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {

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
            viewModel.initPokemon(id = pokemonId)
        }

        state?.let {
            PokemonDetailsScreen(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer),
                onEvent = viewModel::handleEvent,
                state = it,
            )
        }
    }
}