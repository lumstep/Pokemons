package pokemonDetails.presentation.mvi

import core.domain.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pokemonDetails.domain.PokemonDetailInfoModel
import pokemonDetails.domain.PokemonRepository
import pokemonDetails.presentation.mvi.PokemonDetailsState.AvatarTypes
import pokemonDetails.presentation.mvi.mapper.toPokemonDetailsState

class PokemonDetailsViewModel(
    private val pokemonRepository: PokemonRepository,
) {

    private val _effects: Channel<PokemonDetailsEffects> = Channel()
    val effects = _effects.receiveAsFlow()

    private val _state: MutableStateFlow<PokemonDetailsState?> = MutableStateFlow(null)
    val state = _state.asStateFlow()

    private var currentPokemon: PokemonDetailInfoModel? = null
    private var selectedType: AvatarTypes = AvatarTypes.HOME

    fun initPokemon(id: Int) {
        if (currentPokemon != null) return
        CoroutineScope(Dispatchers.Unconfined).launch {
            loadPokemon(
                id = id,
                type = AvatarTypes.HOME,
            )
        }
    }

    fun handleEvent(event: PokemonDetailsEvents) {
        CoroutineScope(Dispatchers.Unconfined).launch {
            when (event) {
                PokemonDetailsEvents.OnArtWorkTypePressed -> selectType(AvatarTypes.ART)
                PokemonDetailsEvents.OnBackPressed -> _effects.send(PokemonDetailsEffects.NavigateBack)
                PokemonDetailsEvents.OnHomeTypePressed -> selectType(AvatarTypes.HOME)
                PokemonDetailsEvents.OnNextPressed -> currentPokemon?.let { pokemon ->
                    loadPokemon(
                        id = pokemon.id + 1,
                        type = selectedType,
                    )
                }

                PokemonDetailsEvents.OnPreviousPressed -> currentPokemon?.let { pokemon ->
                    loadPokemon(
                        id = pokemon.id - 1,
                        type = selectedType,
                    )
                }
            }
        }
    }

    private suspend fun loadPokemon(
        id: Int,
        type: AvatarTypes,
    ) {
        withContext(Dispatchers.IO) {
            when (val resource = pokemonRepository.getPokemonInfo(id)) {
                is Resource.Error -> {}
                is Resource.Success -> {
                    currentPokemon = resource.data
                    selectedType = type
                    _state.emit(resource.data.toPokemonDetailsState(type))
                }
            }
        }
    }

    private suspend fun selectType(
        type: AvatarTypes,
    ) {
        _state.emit(currentPokemon?.toPokemonDetailsState(type)) //TODO don't map again
        selectedType = type
    }
}