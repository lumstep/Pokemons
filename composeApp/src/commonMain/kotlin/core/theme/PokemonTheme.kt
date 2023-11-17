package core.theme

import androidx.compose.runtime.Composable

@Composable
expect fun PokemonTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)