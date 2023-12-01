package pokemonList.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.pop
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import com.bumble.appyx.navigation.node.node
import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize
import org.koin.core.component.KoinComponent
import pokemonDetails.presentation.PokemonDetailsNode
import pokemonList.di.PokemonListScope

class PokemonListNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = NavTarget.PokemonList,
            savedStateMap = buildContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
) : ParentNode<PokemonListNode.NavTarget>(
    buildContext = buildContext,
    appyxComponent = backStack,
), KoinComponent {

    private val scope = PokemonListScope().scope
    private val viewModel: PokemonListViewModel by scope.inject()

    sealed class NavTarget : Parcelable {
        @Parcelize
        data class PokemonDetails(val pokemonId: Int) : NavTarget()

        @Parcelize
        data object PokemonList : NavTarget()
    }

    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            is NavTarget.PokemonDetails -> PokemonDetailsNode(
                pokemonId = interactionTarget.pokemonId,
                buildContext = buildContext,
                navigateBack = { backStack.pop() },
            )

            is NavTarget.PokemonList -> node(buildContext = buildContext) {
                PokemonListScreen(
                    pokemons = viewModel.getPokemons().collectAsLazyPagingItems(),
                    onItemClick = { pokemonId ->
                        backStack.push(NavTarget.PokemonDetails(pokemonId = pokemonId))
                    },
                )
            }
        }

    @Composable
    override fun View(modifier: Modifier) {
        AppyxComponent(
            modifier = Modifier.fillMaxSize(),
            appyxComponent = backStack,
        )
    }
}
