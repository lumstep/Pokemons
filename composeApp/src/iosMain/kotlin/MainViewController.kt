import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark

    val root = remember {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        root = root,
    )
}