package org.skyfaced.characters.vm

import org.skyfaced.data.model.Character
import org.skyfaced.data.model.Info

sealed interface CharactersUiState {
    data class Success(
        val info: Info,
        val characters: List<Character>,
        val additionalInfo: Character? = null,
        val isAdditionalInfoVisible: Boolean = false,
    ) : CharactersUiState

    object Loading : CharactersUiState

    object Failure : CharactersUiState
}