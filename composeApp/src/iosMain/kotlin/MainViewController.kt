import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import core.configs.PokemonRootComponent
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark

    val root = remember {
        PokemonRootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        root = root,
    )
}