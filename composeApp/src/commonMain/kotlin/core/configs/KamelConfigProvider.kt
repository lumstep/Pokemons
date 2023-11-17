package core.configs

import androidx.compose.runtime.Composable
import io.kamel.core.config.KamelConfig

@Composable
expect fun kamelConfigProvider(): KamelConfig
