package org.skyfaced.episodes.vm

import org.skyfaced.data.model.Episode
import org.skyfaced.data.model.Info

sealed interface EpisodeUiState {
    data class Success(
        val info: Info,
        val episodes: List<Episode>
    ) : EpisodeUiState

    object Loading : EpisodeUiState

    object Failure : EpisodeUiState
}
