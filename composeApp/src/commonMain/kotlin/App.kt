import androidx.compose.runtime.Composable
import com.bumble.appyx.navigation.integration.NodeFactory
import com.bumble.appyx.navigation.node.Node
import core.configs.InitialConfigsProvider
import core.configs.PokemonParentNode
import core.theme.PokemonTheme
import pokemonList.presentation.PokemonListNode

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    nodeHostProvider: @Composable (factory: NodeFactory<in Node>) -> Unit,
) {
    PokemonTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        InitialConfigsProvider {
            nodeHostProvider {
                PokemonParentNode(buildContext = it)
            }
        }
    }
}
