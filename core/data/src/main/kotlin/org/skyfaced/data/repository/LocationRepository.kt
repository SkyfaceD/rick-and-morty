package org.skyfaced.data.repository

import kotlinx.coroutines.flow.Flow
import org.skyfaced.data.model.InfoWrapper
import org.skyfaced.data.model.Location
import org.skyfaced.network.model.filter.LocationFilter

interface LocationRepository {
    /**
     * Gets the available [Location] as a flow
     */
    fun getAllLocationsFlow(page: Int, filter: LocationFilter): Flow<InfoWrapper<Location>>

    /**
     * Gets data for a specific [Location] as a flow
     */
    fun getSingleLocationFlow(id: Int): Flow<Location>
}