import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.bumble.appyx.navigation.integration.IosNodeHost
import com.bumble.appyx.navigation.integration.MainIntegrationPoint
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

val backEvents: Channel<Unit> = Channel()
private val integrationPoint = MainIntegrationPoint()

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
    ) { factory ->
        IosNodeHost(
            modifier = Modifier,
            onBackPressedEvents = backEvents.receiveAsFlow(),
            factory = factory,
            integrationPoint = integrationPoint,
        )
    }
}.also { uiViewController ->
    integrationPoint.setViewController(uiViewController)
}