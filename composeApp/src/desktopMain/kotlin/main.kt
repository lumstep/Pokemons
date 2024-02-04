import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import core.di.initKoin
import core.ui.navigation.PokemonRootComponent
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalDecomposeApi::class, ExperimentalResourceApi::class)
fun main() {
    Napier.base(DebugAntilog())

    initKoin()

    val lifecycle = LifecycleRegistry()
    val root = PokemonRootComponent(DefaultComponentContext(lifecycle))

    application {

        val windowState = rememberWindowState(size = DpSize(480.dp, 640.dp))
        LifecycleController(lifecycle, windowState)

        Window(
            title = "Pokemons",
            //icon = painterResource(Res.im),
            state = windowState,
            onCloseRequest = ::exitApplication,
        ) {
            App(
                darkTheme = true,
                dynamicColor = false,
                root = root,
            )
        }
    }
}
