package pokemonDetails.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import core.configs.PokemonRootComponent
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.component.KoinComponent
import pokemonDetails.di.PokemonDetailsScope
import pokemonDetails.presentation.PokemonDetailsScreen
import pokemonDetails.presentation.mvi.PokemonDetailsEffects
import pokemonDetails.presentation.mvi.PokemonDetailsViewModel

class PokemonDetailsComponent(
    componentContext: ComponentContext,
    private val pokemonId: Int,
    private val navigateBack: () -> Unit,
) : ComponentContext by componentContext, KoinComponent, PokemonRootComponent.Child {

    private val scope = PokemonDetailsScope().scope
    private val viewModel: PokemonDetailsViewModel by scope.inject()

    @Composable
    override fun View(modifier: Modifier) {

        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.initPokemon(id = pokemonId)

            viewModel.effects.collectLatest { effect ->
                when (effect) {
                    PokemonDetailsEffects.NavigateBack -> navigateBack()
                }
            }
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