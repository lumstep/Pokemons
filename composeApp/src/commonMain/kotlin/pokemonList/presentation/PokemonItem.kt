package pokemonList.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
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
import core.ui.imageLoading.ImageLoader

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    url: String,
    color: Color,
    name: String,
    number: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageLoader(
            modifier = Modifier
                .background(color)
                .fillMaxWidth()
                .aspectRatio(1.0f),
            url = url,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = number,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = name,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.inverseSurface,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}