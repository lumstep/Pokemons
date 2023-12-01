package pokemonDetails.di

import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.newScope
import org.koin.core.scope.Scope

class PokemonDetailsScope : KoinScopeComponent {
    override val scope: Scope by newScope()

    fun close(){
        scope.close() // don't forget to close current scope
    }
}