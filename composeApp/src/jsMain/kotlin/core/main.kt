import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import core.di.initKoin
import core.ui.navigation.PokemonRootComponent
import kotlinx.browser.document
import org.w3c.dom.Document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()

    val lifecycle = LifecycleRegistry()
    val root =
        PokemonRootComponent(componentContext = DefaultComponentContext(lifecycle = lifecycle))

    lifecycle.attachToDocument()

    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        App(
            dynamicColor = false,
            darkTheme = true,
            root = root,
        )
    }
}

// Attaches the LifecycleRegistry to the document
private fun LifecycleRegistry.attachToDocument() {
    fun onVisibilityChanged() {
        if (document.visibilityState == "visible") {
            resume()
        } else {
            stop()
        }
    }

    onVisibilityChanged()

    document.addEventListener(type = "visibilitychange", callback = { onVisibilityChanged() })
}

private val Document.visibilityState: String
    get() = asDynamic().visibilityState.unsafeCast<String>()