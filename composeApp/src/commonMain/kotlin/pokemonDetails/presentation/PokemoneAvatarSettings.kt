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
import core.util.capitalize
import pokemonDetails.presentation.mvi.PokemonDetailsState

@Composable
fun PokemoneAvatarSettings(
    modifier: Modifier = Modifier,
    selectedType: PokemonDetailsState.AvatarTypes,
    onClick: (type: PokemonDetailsState.AvatarTypes) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PokemonDetailsState.AvatarTypes.entries.forEach { type ->
            OutlinedButton(
                modifier = Modifier.width(100.dp),
                enabled = selectedType != type,
                onClick = { onClick(type) },
            ) {
                Text(text = type.name.capitalize())
            }
        }
    }
}