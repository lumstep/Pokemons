import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import core.configs.InitialConfigsProvider
import core.ui.navigation.PokemonRootComponent
import core.ui.theme.PokemonTheme

@Composable
fun App(
    seedColor: Color,
    useDarkTheme: Boolean,
    root: PokemonRootComponent,
) {
    PokemonTheme(
        seedColor = seedColor,
        useDarkTheme = useDarkTheme,
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
