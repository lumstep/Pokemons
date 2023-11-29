package core.configs

import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.ParentNode
import com.bumble.appyx.utils.multiplatform.Parcelable
import pokemonList.presentation.PokemonListNode
import pokemonList.presentation.PokemonListScreen

class PokemonParentNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = NavTarget.PokemonList,
            savedStateMap = buildContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
) : ParentNode<NavTarget>(
    buildContext = buildContext,
    appyxComponent = backStack,
) {
    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            is NavTarget.PokemonList -> PokemonListNode(buildContext = buildContext)
        }

    @Composable
    override fun View(modifier: Modifier) {
        AppyxComponent(
            modifier = Modifier.fillMaxSize(),
            appyxComponent = backStack,
        )
    }
}

private sealed class NavTarget : Parcelable {
    @Parcelize
    data object PokemonList : NavTarget()
}