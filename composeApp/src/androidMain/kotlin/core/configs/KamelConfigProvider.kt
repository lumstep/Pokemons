package core.configs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default
import io.kamel.image.config.resourcesFetcher
import io.kamel.image.config.resourcesIdMapper

@Composable
actual fun kamelConfigProvider(): KamelConfig {
    val context = LocalContext.current

    val androidConfig = KamelConfig {
        takeFrom(KamelConfig.Default)
        resourcesFetcher(context)
        resourcesIdMapper(context)
    }

    return androidConfig
}
