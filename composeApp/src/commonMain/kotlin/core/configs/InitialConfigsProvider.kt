package core.configs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.kamel.image.config.LocalKamelConfig

@Composable
fun InitialConfigsProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalKamelConfig provides kamelConfigProvider()) {
        content()
    }
}
