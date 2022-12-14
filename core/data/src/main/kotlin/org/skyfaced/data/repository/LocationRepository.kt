package org.skyfaced.data.repository

import kotlinx.coroutines.flow.Flow
import org.skyfaced.data.model.InfoWrapper
import org.skyfaced.data.model.Location
import org.skyfaced.network.model.filter.LocationFilter

interface LocationRepository {
    /**
     * Gets the available [Location] as a flow
     */
    fun getPagedLocationsFlow(page: Int, filter: LocationFilter): Flow<InfoWrapper<Location>>

    /**
     * Gets data for a specific [Location] as a flow
     */
    fun getSingleLocationFlow(id: Int): Flow<Location>

    /**
     * Gets the available [Location] as a flow
     */
    fun getMultipleLocationFlow(ids: List<Int>): Flow<List<Location>>
}