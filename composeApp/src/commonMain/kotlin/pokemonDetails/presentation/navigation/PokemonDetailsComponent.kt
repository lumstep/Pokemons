package pokemonDetails.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import core.ui.navigation.PokemonRootComponent
import core.ui.snackbar.ErrorSnackbarHost
import core.ui.viewModel.koinKmpViewModel
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

    @Composable
    override fun View(modifier: Modifier) {

        val viewModel: PokemonDetailsViewModel = koinKmpViewModel { PokemonDetailsScope() }

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
                sendEvent = viewModel::handleEvent,
                state = state,
            )
        }
    }
}