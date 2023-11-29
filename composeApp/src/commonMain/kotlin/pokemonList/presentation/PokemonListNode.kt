package pokemonList.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize
import pokemonDetails.presentation.PokemonDetailsNode

class PokemonListNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = PokemonList,
            savedStateMap = buildContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
) : ParentNode<NavTarget>(
    buildContext = buildContext,
) {
    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            is NavTarget.PokemonDetails -> PokemonDetailsNode(
                pokemonId = interactionTarget.pokemonId,
                buildContext = buildContext,
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

private sealed class NavTarget : Parcelable {
    @Parcelize
    data class PokemonDetails(val pokemonId: Int) : NavTarget()
}