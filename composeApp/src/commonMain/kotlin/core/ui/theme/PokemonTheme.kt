package core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.materialkolor.AnimatedDynamicMaterialTheme

@Composable
fun PokemonTheme(
    seedColor: Color,
    useDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedDynamicMaterialTheme(
        useDarkTheme = useDarkTheme,
        seedColor = seedColor,
        content = content,
    )
}