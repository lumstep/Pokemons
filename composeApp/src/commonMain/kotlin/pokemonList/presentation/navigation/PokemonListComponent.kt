package pokemonList.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.arkivanov.decompose.ComponentContext
import core.ui.navigation.PokemonRootComponent
import org.koin.core.component.KoinComponent
import pokemonList.di.PokemonListScope
import pokemonList.presentation.PokemonListScreen
import pokemonList.presentation.PokemonListViewModel

class PokemonListComponent(
    componentContext: ComponentContext,
    private val onNavigateToPokemonDetails: (pokemonId: Int) -> Unit,
) : ComponentContext by componentContext, KoinComponent, PokemonRootComponent.Child {

    private val scope = PokemonListScope().scope
    private val viewModel: PokemonListViewModel by scope.inject()

    @Composable
    override fun View(modifier: Modifier) {
        PokemonListScreen(
            modifier = modifier,
            pokemons = viewModel.getPokemons().collectAsLazyPagingItems(),
            onItemClick = onNavigateToPokemonDetails,
        )
    }
}
