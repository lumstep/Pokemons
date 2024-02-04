import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import core.ui.navigation.PokemonRootComponent
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark

    val root = remember {
        PokemonRootComponent(DefaultComponentContext(LifecycleRegistry()))
    }

    LaunchedEffect(Unit){
        Napier.base(DebugAntilog())
    }

    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        root = root,
    )
}