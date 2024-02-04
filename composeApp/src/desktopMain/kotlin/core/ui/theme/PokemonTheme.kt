package core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import core.ui.style.DarkColorScheme
import core.ui.style.LightColorScheme
import core.ui.style.PokemonTypography

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