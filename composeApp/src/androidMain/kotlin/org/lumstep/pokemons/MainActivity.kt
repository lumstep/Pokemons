package org.lumstep.pokemons

import App
import android.app.WallpaperColors
import android.app.WallpaperManager
import android.app.WallpaperManager.FLAG_LOCK
import android.app.WallpaperManager.FLAG_SYSTEM
import android.app.WallpaperManager.OnColorsChangedListener
import android.graphics.Color as AndroidColor
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import core.ui.navigation.PokemonRootComponent
import core.ui.style.GreenPrimaryLight
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O_MR1)

class MainActivity : AppCompatActivity() {

   lateinit var m : WallpaperManager

    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m = WallpaperManager.getInstance(this@MainActivity)

        enableEdgeToEdge()

        Napier.base(DebugAntilog())

        val root = retainedComponent { PokemonRootComponent(it) }
        setContent {
            var k by remember {
                mutableStateOf(
                    m.getWallpaperColors(FLAG_SYSTEM)?.primaryColor?.convertAndroidColorToCompose()
                        ?: GreenPrimaryLight
                )
            }

            LaunchedEffect(key1 = Unit) {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    k =
                        m.getWallpaperColors(FLAG_SYSTEM)?.primaryColor?.convertAndroidColorToCompose()
                            ?: GreenPrimaryLight
                }
            }

            App(
                seedColor = k,
                useDarkTheme = isSystemInDarkTheme(),
                root = root,
            )
        }
    }

    fun AndroidColor.convertAndroidColorToCompose(): Color {
        val red = this.red().roundToInt()
        val green = this.green().roundToInt()
        val blue = this.blue().roundToInt()
        val alpha = this.alpha().roundToInt()

        return Color(red = red, green = green, blue = blue, alpha = alpha)
    }
}

@Preview
@Composable
private fun Preview() {

}