import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        Napier.base(DebugAntilog())
        App(
            darkTheme = true,
            dynamicColor = false,
        )
    }
}

