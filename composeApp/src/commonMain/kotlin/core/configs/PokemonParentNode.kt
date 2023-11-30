package core.configs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize
import pokemonList.presentation.PokemonListNode

class PokemonParentNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = NavTarget.PokemonList,
            savedStateMap = buildContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
) : ParentNode<PokemonParentNode.NavTarget>(
    buildContext = buildContext,
    appyxComponent = backStack,
) {
    sealed class NavTarget : Parcelable {
        @Parcelize
        data object PokemonList : NavTarget()
    }

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

