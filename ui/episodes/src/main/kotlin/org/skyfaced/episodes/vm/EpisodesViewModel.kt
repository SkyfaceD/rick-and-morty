package org.skyfaced.episodes.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.skyfaced.common.Result
import org.skyfaced.common.asResult
import org.skyfaced.data.repository.EpisodeRepository
import org.skyfaced.episodes.util.INITIAL_PAGE
import org.skyfaced.network.model.filter.EpisodeFilter
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository
) : ViewModel() {
    private val _episodeUiState = MutableStateFlow<EpisodeUiState>(EpisodeUiState.Loading)
    val episodeUiState = _episodeUiState.asStateFlow()

    init {
        viewModelScope.launch { fetchEpisodes() }
    }

    private suspend fun fetchEpisodes() {
        _episodeUiState.emitAll(
            episodeUiState(
                page = INITIAL_PAGE,
                episodeRepository = episodeRepository
            )
        )
    }
}

// TODO Add paging
private fun episodeUiState(
    page: Int,
    episodeRepository: EpisodeRepository,
    episodeFilter: EpisodeFilter = EpisodeFilter(),
): Flow<EpisodeUiState> {
    val pagedEpisodesFlow = episodeRepository.getPagedEpisodesFlow(page, episodeFilter)

    return pagedEpisodesFlow.asResult()
        .map {
            when (it) {
                is Result.Success -> EpisodeUiState.Success(it.data.info, it.data.data)
                is Result.Failure -> EpisodeUiState.Failure
                is Result.Loading -> EpisodeUiState.Loading
            }
        }
}