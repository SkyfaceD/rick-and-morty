package org.skyfaced.data.repository

import kotlinx.coroutines.flow.Flow
import org.skyfaced.data.model.Episode
import org.skyfaced.data.model.InfoWrapper
import org.skyfaced.network.model.filter.EpisodeFilter

interface EpisodeRepository {
    /**
     * Gets the available [Episode] as a flow
     */
    fun getAllEpisodesFlow(page: Int, filter: EpisodeFilter): Flow<InfoWrapper<Episode>>

    /**
     * Gets data for a specific [Episode] as a flow
     */
    fun getSingleEpisodeFlow(id: Int): Flow<Episode>

    /**
     * Gets the available [Episode] as a flow
     */
    fun getMultipleEpisodeFlow(ids: List<Int>): Flow<List<Episode>>
}