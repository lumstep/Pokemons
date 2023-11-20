package pokemonDetails.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val DEFAULT_WIDTH = 100.dp

private val buttonNextPath: Path.(size: Size) -> Path = { size ->
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
    this
}

private val buttonNextShape: Shape = GenericShape { size, _ ->
    buttonNextPath(size)
}

private val buttonPreviousPath: Path.(size: Size) -> Path = { size ->
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
    this
}

private val buttonPreviousShape: Shape = GenericShape { size, _ ->
    buttonPreviousPath(size)
}

sealed interface NavigateButtonType {
    object Next : NavigateButtonType

    object Previous : NavigateButtonType
}

@Composable
fun NavigateButton(
    modifier: Modifier = Modifier,
    navigateButtonType: NavigateButtonType,
    width: Dp = DEFAULT_WIDTH,
    onClick: () -> Unit,
) {
    val pathBuilder = remember {
        when (navigateButtonType) {
            NavigateButtonType.Next -> buttonNextPath
            NavigateButtonType.Previous -> buttonPreviousPath
        }
    }
    val shape = remember {
        when (navigateButtonType) {
            NavigateButtonType.Next -> buttonNextShape
            NavigateButtonType.Previous -> buttonPreviousShape
        }
    }

    Box(
        modifier = modifier
            .width(width)
            .aspectRatio(0.5f)
            .clip(shape)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    radius = 30.dp,
                    color = Color.Red,
                ),
            )
            .drawBehind {
                drawPath(
                    path = Path().pathBuilder(size),
                    color = Color.Black,
                )
            },
    ) {
        Icon(
            modifier = Modifier
                .then(
                    when (navigateButtonType) {
                        NavigateButtonType.Next -> Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 28.dp)

                        NavigateButtonType.Previous -> Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 28.dp)
                    }
                )
                .size(24.dp),
            imageVector = when (navigateButtonType) {
                NavigateButtonType.Next -> Icons.Default.ArrowForward
                NavigateButtonType.Previous -> Icons.Default.ArrowBack
            },
            tint = Color.White,
            contentDescription = null,
        )
    }
}