package pokemonDetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemoneAvatarSettings(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onArtClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedButton(
            modifier = Modifier.width(100.dp),
            onClick = onHomeClick,
        ) {
            Text(text = "Home")
        }

        OutlinedButton(
            modifier = Modifier.width(100.dp),
            onClick = onArtClick,
        ) {
            Text(text = "Art")
        }
    }
}