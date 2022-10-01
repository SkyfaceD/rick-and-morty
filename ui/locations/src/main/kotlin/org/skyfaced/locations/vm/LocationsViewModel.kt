package org.skyfaced.locations.vm

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
import org.skyfaced.data.model.InfoWrapper
import org.skyfaced.data.model.Location
import org.skyfaced.data.repository.LocationRepository
import org.skyfaced.locations.util.INITIAL_PAGE
import org.skyfaced.network.model.filter.LocationFilter
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {
    private val _locationUiState = MutableStateFlow<LocationUiState>(LocationUiState.Loading)
    val locationUiState = _locationUiState.asStateFlow()

    init {
        viewModelScope.launch { fetchLocations() }
    }

    private suspend fun fetchLocations() {
        _locationUiState.emitAll(
            locationUiState(
                page = INITIAL_PAGE,
                locationRepository = locationRepository,
            )
        )
    }
}

// TODO Add paging
private fun locationUiState(
    page: Int,
    locationRepository: LocationRepository,
    locationFilter: LocationFilter = LocationFilter(),
): Flow<LocationUiState> {
    val pagedLocationsFlow: Flow<InfoWrapper<Location>> =
        locationRepository.getPagedLocationsFlow(page, locationFilter)

    return pagedLocationsFlow.asResult()
        .map { result ->
            when (result) {
                is Result.Success -> LocationUiState.Success(result.data.info, result.data.data)
                is Result.Failure -> LocationUiState.Failure
                is Result.Loading -> LocationUiState.Loading
            }
        }
}