package core.configs

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun screenSizeProvider(): IntSize = LocalWindowInfo.current.containerSize