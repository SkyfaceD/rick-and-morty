package org.skyfaced.locations.vm

import org.skyfaced.data.model.Info
import org.skyfaced.data.model.Location

sealed interface LocationUiState {
    data class Success(
        val info: Info,
        val locations: List<Location>
    ) : LocationUiState

    object Loading : LocationUiState

    object Failure : LocationUiState
}