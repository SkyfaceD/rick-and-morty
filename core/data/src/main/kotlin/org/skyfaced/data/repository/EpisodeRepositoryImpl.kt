package org.skyfaced.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.skyfaced.data.model.Episode
import org.skyfaced.data.model.InfoWrapper
import org.skyfaced.data.model.asModel
import org.skyfaced.data.model.wrap
import org.skyfaced.network.RMNetworkDataSource
import org.skyfaced.network.model.EpisodeDto
import org.skyfaced.network.model.filter.EpisodeFilter
import javax.inject.Inject

// TODO Replace hardcoded dispatcher
class EpisodeRepositoryImpl @Inject constructor(
    private val network: RMNetworkDataSource
) : EpisodeRepository {
    override fun getPagedEpisodesFlow(
        page: Int,
        filter: EpisodeFilter
    ): Flow<InfoWrapper<Episode>> = flow {
        val response = network.getPagedEpisodes(page, filter)
        val info = response.info.asModel()
        val characters = response.data.map(EpisodeDto::asModel)
        val result = info wrap characters
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getSingleEpisodeFlow(id: Int): Flow<Episode> = flow {
        val response = network.getSingleEpisode(id)
        val result = response.asModel()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getMultipleEpisodeFlow(ids: List<Int>): Flow<List<Episode>> = flow {
        val response = network.getMultipleEpisodes(ids)
        val result = response.map(EpisodeDto::asModel)
        emit(result)
    }.flowOn(Dispatchers.IO)
}