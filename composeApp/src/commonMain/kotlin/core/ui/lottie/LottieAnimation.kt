package core.ui.lottie

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun LottieAnimationLoader(modifier: Modifier = Modifier, json: String)