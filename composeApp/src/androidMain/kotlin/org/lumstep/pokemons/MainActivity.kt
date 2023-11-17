package org.lumstep.pokemons

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import pokemonDetails.presentation.NavigateNextButton
import pokemonDetails.presentation.NavigatePreviousButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            App(
                dynamicColor = true,
                darkTheme = isSystemInDarkTheme(),
            )
        }
    }
}

@Preview
@Composable
fun NavigateNextButtonPreview() {
    NavigateNextButton {}
    NavigatePreviousButton(modifier = Modifier.rotate(180f)) {}
}