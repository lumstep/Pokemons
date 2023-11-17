package pokemonDetails.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import core.imageLoading.ImageLoader
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

private val ANIMATION_DELAY = 5.seconds
private const val ANIMATION_DURATION = 2000

@Composable
fun PokemonImageBig(
    modifier: Modifier = Modifier,
    url: String,
    shinyUrl: String,
) {
    var isShiny by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isShiny) {
        delay(ANIMATION_DELAY)
        isShiny = !isShiny
    }

    val backgroundColor = MaterialTheme.colorScheme.inverseSurface
    Box(
        modifier = modifier
            .aspectRatio(1.0f)
            .padding(horizontal = 20.dp)
            .drawBehind {
                drawCircle(
                    Brush.radialGradient(
                        0.0f to backgroundColor.copy(alpha = 0.4f),
                        0.9f to backgroundColor.copy(alpha = 0.1f),
                        1.0f to backgroundColor.copy(alpha = 0.0f),
                    )
                )
            },
    ) {
        ImageLoader(data = url, onLoading = null)
        AnimatedVisibility(
            visible = isShiny,
            enter = fadeIn(tween(ANIMATION_DURATION)),
            exit = fadeOut(tween(ANIMATION_DURATION)),
        ) {
            ImageLoader(data = shinyUrl, onLoading = null)
        }
    }
}
