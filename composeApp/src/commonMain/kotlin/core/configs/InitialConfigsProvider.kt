package core.configs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun InitialConfigsProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider() {
        content()
    }
}
