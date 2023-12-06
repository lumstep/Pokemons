import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import core.configs.InitialConfigsProvider
import core.configs.PokemonRootComponent
import core.theme.PokemonTheme

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    root: PokemonRootComponent,
) {
    PokemonTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        InitialConfigsProvider {
            val childStack by root.childStack.subscribeAsState()
            Children(
                stack = childStack,
                animation = stackAnimation(slide())
            ) { child ->
                child.instance.View(Modifier)
            }
        }
    }
}
