package core.ui.imageLoading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
    alpha: Float = DefaultAlpha,
    onLoading: @Composable (BoxScope.() -> Unit)? = {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    },
    onFailure: @Composable (BoxScope.() -> Unit)? = null,
) {
   /* KamelImage(
        modifier = modifier,
        resource = asyncPainterResource(data),
        contentDescription = contentDescription,
        colorFilter = colorFilter,
        onLoading = onLoading,
        onFailure = onFailure,
        alpha = alpha,
        contentScale = contentScale,
    )*/
    Box {

        var state: AsyncImagePainter.State by remember {
            mutableStateOf(AsyncImagePainter.State.Empty)
        }

        if (state is AsyncImagePainter.State.Loading) {
            onLoading?.invoke(this)
        }
        if (state is AsyncImagePainter.State.Error) {
            onFailure?.invoke(this)
        }

        AsyncImage(
            modifier = modifier,
            model = url,
            contentDescription = contentDescription,
            colorFilter = colorFilter,
            alpha = alpha,
            contentScale = contentScale,
            onState = { newState -> state = newState },
        )
    }
}
