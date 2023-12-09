package org.lumstep.pokemons

import App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import core.ui.navigation.PokemonRootComponent
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        Napier.base(DebugAntilog())

        val root = retainedComponent { PokemonRootComponent(it) }
        setContent {
            App(
                dynamicColor = true,
                darkTheme = isSystemInDarkTheme(),
                root = root,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {

}