package pokemonList.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.arkivanov.decompose.ComponentContext
import com.hoc081098.kmp.viewmodel.compose.kmpViewModel
import com.hoc081098.kmp.viewmodel.viewModelFactory
import core.ui.navigation.PokemonRootComponent
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.component.KoinComponent
import pokemonList.di.PokemonListScope
import pokemonList.presentation.PokemonListScreen
import pokemonList.presentation.PokemonListViewModel
import pokemonList.presentation.mvi.PokemonListEffects

class PokemonListComponent(
    componentContext: ComponentContext,
    private val navigateToPokemonDetails: (pokemonId: Int) -> Unit,
) : ComponentContext by componentContext, KoinComponent, PokemonRootComponent.Child {

    init {
        Napier.d { "init PokemonListComponent" }
    }

    private val scope = PokemonListScope().scope

    @Composable
    override fun View(modifier: Modifier) {

        val viewModel: PokemonListViewModel = kmpViewModel(
            factory = viewModelFactory {
                Napier.d { "viewModelFactory PokemonListViewModel" }
                scope.get<PokemonListViewModel>().apply {
                    addCloseable {
                        Napier.d { "onCleared PokemonListViewModel" }
                        scope.close()
                    }
                }
            },
        )

        LaunchedEffect(Unit) {
            viewModel.effects.collectLatest { effect ->
                when (effect) {
                    is PokemonListEffects.NavigateToPokemonDetails -> navigateToPokemonDetails(effect.pokemonId)
                }
            }
        }

        PokemonListScreen(
            modifier = modifier,
            pokemons = viewModel.getPokemons().collectAsLazyPagingItems(),
            sendEvent = viewModel::handleEvent,
        )
    }
}
