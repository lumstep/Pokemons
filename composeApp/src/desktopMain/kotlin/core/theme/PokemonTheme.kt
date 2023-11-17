package core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import core.style.DarkColorScheme
import core.style.LightColorScheme
import core.style.PokemonTypography


@Composable
actual fun PokemonTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = PokemonTypography,
        content = content
    )
}