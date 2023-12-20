package pokemonDetails.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import core.ui.snackbar.ErrorSnackbarHost
import core.ui.navigation.PokemonRootComponent
import io.github.aakira.napier.Napier
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
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            viewModel.initPokemon(id = pokemonId)

            viewModel.effects.collectLatest { effect ->
                when (effect) {
                    is PokemonDetailsEffects.NavigateBack -> navigateBack()
                    is PokemonDetailsEffects.Error -> {
                        val result = snackbarHostState.showSnackbar(
                            message = effect.message,
                            duration = SnackbarDuration.Short,
                            withDismissAction = true,
                            actionLabel = "Retry",
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            Napier.d { "ActionPerformed" }
                            effect.retry.invoke()
                        }
                    }
                }
            }
        }

        Scaffold(
            snackbarHost = {
                ErrorSnackbarHost(hostState = snackbarHostState)
            }
        ) {
            PokemonDetailsScreen(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer),
                onEvent = viewModel::handleEvent,
                state = state,
            )
        }
    }
}