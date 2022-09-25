package org.skyfaced.characters.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.skyfaced.characters.util.INITIAL_PAGE
import org.skyfaced.characters.util.MAX_CHARACTERS_COUNT
import org.skyfaced.characters.util.MIN_HEADER_ITEM
import org.skyfaced.common.Result
import org.skyfaced.common.asResult
import org.skyfaced.data.model.Character
import org.skyfaced.data.model.InfoWrapper
import org.skyfaced.data.repository.CharacterRepository
import org.skyfaced.network.model.filter.CharacterFilter
import javax.inject.Inject
import kotlin.random.Random.Default.nextInt

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
) : ViewModel() {
    private val _headerUiState = MutableStateFlow<HeaderUiState>(HeaderUiState.Loading)
    val headerUiState = _headerUiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchHeader()
        }
    }

    val characterUiState: StateFlow<CharactersUiState> = characterUiState(
        page = INITIAL_PAGE,
        characterRepository = characterRepository,
        characterFilter = CharacterFilter(),
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = CharactersUiState.Loading
    )

    fun headerRefresh() {
        viewModelScope.launch {
            fetchHeader()
        }
    }

    private suspend fun fetchHeader() {
        _headerUiState.emitAll(headerUiState(characterRepository = characterRepository))
    }
}

// TODO Combine with user preferences
private fun headerUiState(
    characterRepository: CharacterRepository
): Flow<HeaderUiState> {
    val ids = List(MIN_HEADER_ITEM) { nextInt(MAX_CHARACTERS_COUNT) }
    val multipleCharactersFlow: Flow<List<Character>> =
        characterRepository.getMultipleCharacterFlow(ids)

    return multipleCharactersFlow.asResult()
        .map { result ->
            when (result) {
                is Result.Success -> HeaderUiState.Success(result.data)
                is Result.Failure -> HeaderUiState.Failure
                is Result.Loading -> HeaderUiState.Loading
            }
        }
}

// TODO Add paging
private fun characterUiState(
    page: Int,
    characterRepository: CharacterRepository,
    characterFilter: CharacterFilter = CharacterFilter(),
): Flow<CharactersUiState> {
    val pagedCharactersFlow: Flow<InfoWrapper<Character>> =
        characterRepository.getPagedCharactersFlow(page, characterFilter)

    return pagedCharactersFlow.asResult()
        .map { result ->
            when (result) {
                is Result.Success -> CharactersUiState.Success(result.data.info, result.data.data)
                is Result.Failure -> CharactersUiState.Failure
                is Result.Loading -> CharactersUiState.Loading
            }
        }
}