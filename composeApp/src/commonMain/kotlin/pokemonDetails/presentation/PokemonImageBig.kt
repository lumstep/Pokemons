package pokemonDetails.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import core.ui.imageLoading.ImageLoader
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun PokemonImageBig(
    modifier: Modifier = Modifier,
    url: String,
    shinyUrl: String,
) {
    val backgroundColor = MaterialTheme.colorScheme.inverseSurface

    var isShiny by remember {
        mutableStateOf(false)
    }

    val alpha by animateFloatAsState(
        targetValue = if (isShiny) 1.0f else 0.0f,
        animationSpec = tween(2000)
    )

    LaunchedEffect(isShiny) {
        delay(3.seconds)
        isShiny = !isShiny
    }

    Box(
        modifier = modifier
            .aspectRatio(1.0f)
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
        ImageLoader(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .aspectRatio(1.0f),
            data = url,
            onLoading = null
        )
        ImageLoader(
            modifier = Modifier
                .alpha(alpha)
                .padding(horizontal = 20.dp)
                .aspectRatio(1.0f),
            data = shinyUrl,
            onLoading = null,
        )
    }
}
