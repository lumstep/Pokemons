package pokemonDetails.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import core.ui.lottie.LottieAnimationLoader
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PokemonSparks(
    modifier: Modifier = Modifier,
) {
    var text: String? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
      //  text = resource("fire.json").readBytes().decodeToString()
    }

    text?.let {
        LottieAnimationLoader(modifier = modifier, json = it)
    }
}