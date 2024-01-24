package core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable
import pokemonDetails.presentation.navigation.PokemonDetailsComponent
import pokemonList.presentation.navigation.PokemonListComponent

class PokemonRootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()
    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.PokemonList,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext,
    ): Child {
        return when (config) {
            is Configuration.PokemonDetails -> PokemonDetailsComponent(
                componentContext = context,
                pokemonId = config.pokemonId,
                navigateBack = { navigation.pop() },
            )

            is Configuration.PokemonList -> PokemonListComponent(
                componentContext = context,
                navigateToPokemonDetails = { pokemonId: Int ->
                    navigation.pushNew(Configuration.PokemonDetails(pokemonId = pokemonId))
                },
            )
        }
    }

    interface Child {
        @Composable
        fun View(modifier: Modifier)
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object PokemonList : Configuration()

        @Serializable
        data class PokemonDetails(val pokemonId: Int) : Configuration()
    }
}
