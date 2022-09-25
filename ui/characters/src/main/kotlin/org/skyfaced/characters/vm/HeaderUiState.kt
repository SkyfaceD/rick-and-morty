package org.skyfaced.characters.vm

import org.skyfaced.data.model.Character

sealed interface HeaderUiState {
    data class Success(val characters: List<Character>) : HeaderUiState

    object Loading : HeaderUiState

    object Failure : HeaderUiState
}