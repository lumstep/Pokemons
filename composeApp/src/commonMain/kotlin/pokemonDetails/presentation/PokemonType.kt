package pokemonDetails.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonType(
    modifier: Modifier = Modifier,
    type: String,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .border(
                width = 1.dp,
                color = Color.Red,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            contentDescription = null,
            tint = Color.Red,
            imageVector = Icons.Default.Face,
        )
        Spacer(Modifier.width(16.dp))
        Text(
            color = Color.Red,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            text = type
        )
    }
}