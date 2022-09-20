package org.skyfaced.network

import org.skyfaced.network.model.Character
import org.skyfaced.network.model.Episode
import org.skyfaced.network.model.InfoWrapper
import org.skyfaced.network.model.Location
import org.skyfaced.network.model.filter.CharacterFilter
import org.skyfaced.network.model.filter.EpisodeFilter
import org.skyfaced.network.model.filter.LocationFilter

interface RMNetworkDataSource {
    suspend fun getAllCharacters(page: Int, filter: CharacterFilter): InfoWrapper<Character>

    suspend fun getSingleCharacter(id: Int): Character

    suspend fun getMultipleCharacters(ids: List<String>): List<Character>

    suspend fun getAllLocations(page: Int, filters: LocationFilter): InfoWrapper<Location>

    suspend fun getSingleLocation(id: Int): Location

    suspend fun getMultipleLocations(ids: List<String>): List<Location>

    suspend fun getAllEpisodes(page: Int, filters: EpisodeFilter): InfoWrapper<Episode>

    suspend fun getSingleEpisode(id: Int): Episode

    suspend fun getMultipleEpisodes(ids: List<String>): List<Episode>
}