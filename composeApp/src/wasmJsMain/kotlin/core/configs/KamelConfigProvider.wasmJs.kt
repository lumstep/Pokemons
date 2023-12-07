package core.configs

import androidx.compose.runtime.Composable
import io.kamel.core.config.KamelConfig
import io.kamel.image.config.Default

@Composable
actual fun kamelConfigProvider(): KamelConfig {
    return KamelConfig.Default
}
