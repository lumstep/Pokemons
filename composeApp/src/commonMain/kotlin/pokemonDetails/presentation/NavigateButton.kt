package pokemonDetails.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val DEFAULT_WIDTH = 100.dp

@Composable
fun NavigateNextButton(
    modifier: Modifier = Modifier,
    width: Dp = DEFAULT_WIDTH,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .width(width)
            .aspectRatio(0.5f)
            .clickable(onClick = onClick)
            .drawBehind {
                val path = Path().apply {
                    moveTo(size.width, 0.0f)
                    lineTo(size.width, size.height)
                    cubicTo(
                        size.width * 0.5f,
                        size.height * 0.5f,
                        size.width * 0.15f,
                        size.height * 0.7f,
                        size.width * 0.15f,
                        size.height * 0.5f,
                    )
                    cubicTo(
                        size.width * 0.15f,
                        size.height * 0.3f,
                        size.width * 0.5f,
                        size.height * 0.5f,
                        size.width,
                        0f,
                    )
                    close()
                }
                drawPath(path = path, color = Color.Black)
            },
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 28.dp)
                .size(24.dp),
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.background,
        )
    }
}

@Composable
fun NavigatePreviousButton(
    modifier: Modifier = Modifier,
    width: Dp = DEFAULT_WIDTH,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .width(width)
            .aspectRatio(0.5f)
            .clickable(onClick = onClick)
            .drawBehind {
                val path = Path().apply {
                    lineTo(0f, size.height)
                    cubicTo(
                        size.width * 0.5f,
                        size.height * 0.5f,
                        size.width * 0.85f,
                        size.height * 0.7f,
                        size.width * 0.85f,
                        size.height * 0.5f,
                    )
                    cubicTo(
                        size.width * 0.85f,
                        size.height * 0.3f,
                        size.width * 0.5f,
                        size.height * 0.5f,
                        0f,
                        0f,
                    )
                    close()
                }
                drawPath(path = path, color = Color.Black)
            },
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 28.dp)
                .size(24.dp),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.background,
        )
    }
}