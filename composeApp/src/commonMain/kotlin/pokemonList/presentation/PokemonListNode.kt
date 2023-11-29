package pokemonList.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.pop
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import core.configs.NavTarget
import pokemonDetails.presentation.PokemonDetailsNode

class PokemonListNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget>,
) : ParentNode<NavTarget>(
    buildContext = buildContext,
    appyxComponent = backStack,
) {
    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            is NavTarget.PokemonDetails -> PokemonDetailsNode(
                pokemonId = interactionTarget.pokemonId,
                buildContext = buildContext,
                navigateBack = { backStack.pop() },
            )
        }

    @Composable
    override fun View(modifier: Modifier) {
        PokemonListScreen(
            modifier = modifier,
            onItemClick = { pokemonId: Int ->
                backStack.push(NavTarget.PokemonDetails(pokemonId = pokemonId))
            },
        )
    }
}
