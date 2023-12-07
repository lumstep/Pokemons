package core.imageLoading

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    data: Any,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
    alpha: Float = 1f,
    onLoading: @Composable (BoxScope.(Float) -> Unit)? = {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    },
    onFailure: @Composable (BoxScope.(Throwable) -> Unit)? = null,
) {
    KamelImage(
        modifier = modifier,
        resource = asyncPainterResource(data),
        contentDescription = contentDescription,
        colorFilter = colorFilter,
        onLoading = onLoading,
        onFailure = onFailure,
        alpha = alpha,
        contentScale = contentScale,
    )
}
