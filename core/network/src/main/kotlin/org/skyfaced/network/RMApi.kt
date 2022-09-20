package org.skyfaced.network

import org.skyfaced.network.model.Character
import org.skyfaced.network.model.Episode
import org.skyfaced.network.model.InfoWrapper
import org.skyfaced.network.model.Location
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RMApi {
    /**
     * Available parameters:
     *  name: filter by the given name.
     *  status: filter by the given status (alive, dead or unknown).
     *  species: filter by the given species.
     *  type: filter by the given type.
     *  gender: filter by the given gender (female, male, genderless or unknown).
     *
     * @see <a href="https://rickandmortyapi.com/documentation/#filter-characters">Source</a>
     */
    @GET("character")
    suspend fun getAllCharacters(@QueryMap filters: Map<String, String>): InfoWrapper<Character>

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") id: Int): Character

    /**
     * @param ids should be positive integer values separated by comma.
     */
    @GET("character/[{id}]")
    suspend fun getMultipleCharacters(@Path("ids") ids: String): List<Character>

    /**
     * Available parameters:
     *  name: filter by the given name.
     *  type: filter by the given type.
     *  dimension: filter by the given dimension.
     *
     * @see <a href="https://rickandmortyapi.com/documentation/#filter-locations">Source</a>
     */
    @GET("location")
    suspend fun getAllLocations(@QueryMap filters: Map<String, String>): InfoWrapper<Location>

    @GET("location/{id}")
    suspend fun getSingleLocation(@Path("id") id: Int): Location

    /**
     * @param ids should be positive integer values separated by comma.
     */
    @GET("location/[{id}]")
    suspend fun getMultipleLocations(@Path("ids") ids: String): List<Location>

    /**
     * Available parameters:
     *  name: filter by the given name.
     *  episode: filter by the given episode code.
     *
     * @see <a href="https://rickandmortyapi.com/documentation/#filter-episodes">Source</a>
     */
    @GET("episode")
    suspend fun getAllEpisodes(@QueryMap filters: Map<String, String>): InfoWrapper<Episode>

    @GET("episode/{id}")
    suspend fun getSingleEpisode(@Path("id") id: Int): Episode

    /**
     * @param ids should be positive integer values separated by comma.
     */
    @GET("episode/[{id}]")
    suspend fun getMultipleEpisodes(@Path("ids") ids: String): List<Episode>
}