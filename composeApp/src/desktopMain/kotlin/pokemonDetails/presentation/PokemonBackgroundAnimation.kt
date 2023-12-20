package pokemonDetails.presentation

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import org.jetbrains.compose.animatedimage.Blank
import org.jetbrains.compose.animatedimage.animate
import org.jetbrains.compose.animatedimage.loadResourceAnimatedImage
import org.jetbrains.compose.resources.loadOrNull

@Composable
actual fun PokemonBackgroundAnimation(modifier: Modifier) {
    val url = "winter.gif"
    Image(
        modifier = modifier,
        bitmap = loadOrNull { loadResourceAnimatedImage(url) }?.animate() ?: ImageBitmap.Blank,
        contentDescription = null,
    )
}