package core.configs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
actual fun screenSizeProvider(): IntSize {
    val destiny = LocalDensity.current
    val configuration = LocalConfiguration.current

    return with(destiny) {
        IntSize(
            width = configuration.screenWidthDp.dp.toPx().roundToInt(),
            height = configuration.screenHeightDp.dp.toPx().roundToInt(),
        )
    }
}