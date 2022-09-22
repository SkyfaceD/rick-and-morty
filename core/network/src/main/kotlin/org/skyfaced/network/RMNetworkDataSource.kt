package org.skyfaced.network

import org.skyfaced.network.model.CharacterDto
import org.skyfaced.network.model.EpisodeDto
import org.skyfaced.network.model.InfoDtoWrapper
import org.skyfaced.network.model.LocationDto
import org.skyfaced.network.model.filter.CharacterFilter
import org.skyfaced.network.model.filter.EpisodeFilter
import org.skyfaced.network.model.filter.LocationFilter

interface RMNetworkDataSource {
    suspend fun getAllCharacters(page: Int, filter: CharacterFilter): InfoDtoWrapper<CharacterDto>

    suspend fun getSingleCharacter(id: Int): CharacterDto

    suspend fun getMultipleCharacters(ids: List<String>): List<CharacterDto>

    suspend fun getAllLocations(page: Int, filters: LocationFilter): InfoDtoWrapper<LocationDto>

    suspend fun getSingleLocation(id: Int): LocationDto

    suspend fun getMultipleLocations(ids: List<String>): List<LocationDto>

    suspend fun getAllEpisodes(page: Int, filters: EpisodeFilter): InfoDtoWrapper<EpisodeDto>

    suspend fun getSingleEpisode(id: Int): EpisodeDto

    suspend fun getMultipleEpisodes(ids: List<String>): List<EpisodeDto>
}