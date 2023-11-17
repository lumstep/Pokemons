package pokemonDetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonCharacteristics(
    modifier: Modifier = Modifier,
    weight: String,
    height: String,
    experience: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Characteristic(
            modifier = Modifier.width(100.dp),
            text = "Weight",
            value = weight,
        )
        Characteristic(
            modifier = Modifier.width(100.dp),
            text = "Height",
            value = height,
        )
        Characteristic(
            modifier = Modifier.width(100.dp),
            text = "Experience",
            value = experience,
        )
    }
}