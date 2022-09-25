package org.skyfaced.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.skyfaced.data.model.InfoWrapper
import org.skyfaced.data.model.Location
import org.skyfaced.data.model.asModel
import org.skyfaced.data.model.wrap
import org.skyfaced.network.RMNetworkDataSource
import org.skyfaced.network.model.LocationDto
import org.skyfaced.network.model.filter.LocationFilter
import javax.inject.Inject

// TODO Replace hardcoded dispatcher
class LocationRepositoryImpl @Inject constructor(
    private val network: RMNetworkDataSource
) : LocationRepository {
    override fun getPagedLocationsFlow(
        page: Int,
        filter: LocationFilter
    ): Flow<InfoWrapper<Location>> = flow {
        val response = network.getPagedLocations(page, filter)
        val info = response.info.asModel()
        val characters = response.data.map(LocationDto::asModel)
        val result = info wrap characters
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getSingleLocationFlow(id: Int): Flow<Location> = flow {
        val response = network.getSingleLocation(id)
        val result = response.asModel()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getMultipleLocationFlow(ids: List<Int>): Flow<List<Location>> = flow {
        val response = network.getMultipleLocations(ids)
        val result = response.map(LocationDto::asModel)
        emit(result)
    }.flowOn(Dispatchers.IO)
}