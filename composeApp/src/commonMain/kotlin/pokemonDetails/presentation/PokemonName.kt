package pokemonDetails.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PokemonName(
    modifier: Modifier = Modifier,
    name: String,
) {
    Text(
        modifier = modifier,
        text = name,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = MaterialTheme.colorScheme.inverseSurface,
    )
}