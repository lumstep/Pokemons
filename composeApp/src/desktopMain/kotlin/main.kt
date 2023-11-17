import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache5.Apache5

val client = HttpClient(Apache5)

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App(
            darkTheme = true,
            dynamicColor = false,
        )
    }
}

