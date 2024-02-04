package pokemonDetails.presentation.mvi

import com.hoc081098.kmp.viewmodel.ViewModel
import core.domain.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pokemonDetails.domain.PokemonDetailInfoModel
import pokemonDetails.domain.PokemonRepository
import pokemonDetails.presentation.mvi.PokemonDetailsState.AvatarTypes
import pokemonDetails.presentation.mvi.mapper.toPokemonDetailsState

class PokemonDetailsViewModel(
    private val pokemonRepository: PokemonRepository,
) : ViewModel(){

    private val _effects: Channel<PokemonDetailsEffects> = Channel()
    val effects = _effects.receiveAsFlow()

    private val _state: MutableStateFlow<PokemonDetailsState> = MutableStateFlow(
        PokemonDetailsState(
            isLoading = true,
            name = "",
            type = null,
            weight = "",
            height = "",
            experience = "",
            selectedAvatarType = AvatarTypes.HOME,
            avatar = "",
            shinyAvatar = "",
        )
    )
    val state = _state.asStateFlow()

    private var currentPokemon: PokemonDetailInfoModel? = null
    private var selectedType: AvatarTypes = AvatarTypes.HOME

    fun initPokemon(id: Int) {
        if (currentPokemon != null) return
        viewModelScope.launch {
            loadPokemon(
                id = id,
                type = AvatarTypes.HOME,
            )
        }
    }

    fun handleEvent(event: PokemonDetailsEvents) {
        viewModelScope.launch {
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
        _state.emit(
            PokemonDetailsState(
                name = "",
                type = null,
                weight = "",
                height = "",
                experience = "",
                selectedAvatarType = type,
                avatar = "",
                shinyAvatar = "",
                isLoading = true,
            )
        )

        when (val resource = pokemonRepository.getPokemonInfo(id)) {
            is Resource.Error -> {
                _effects.send(
                    PokemonDetailsEffects.Error(
                        message = resource.message ?: "Exception",
                        retry = {
                            viewModelScope.launch {
                                loadPokemon(id, type)
                            }
                        },
                    )
                )
            }

            is Resource.Success -> {
                currentPokemon = resource.data
                selectedType = type
                _state.emit(resource.data.toPokemonDetailsState(type))
            }
        }
    }

    private suspend fun selectType(
        type: AvatarTypes,
    ) {
        currentPokemon?.let { pokemon ->
            _state.emit(
                _state.value.copy(
                    avatar = when (type) {
                        AvatarTypes.HOME -> pokemon.homeAvatar.orEmpty()
                        AvatarTypes.ART -> pokemon.artWorkAvatar.orEmpty()
                    },
                    shinyAvatar = when (type) {
                        AvatarTypes.HOME -> pokemon.homeShinyAvatar.orEmpty()
                        AvatarTypes.ART -> pokemon.artWorkShinyAvatar.orEmpty()
                    },
                )
            )
        }
        selectedType = type
    }
}