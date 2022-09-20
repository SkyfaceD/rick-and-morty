package org.skyfaced.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import logcat.logcat
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.skyfaced.network.model.Character
import org.skyfaced.network.model.Episode
import org.skyfaced.network.model.InfoWrapper
import org.skyfaced.network.model.Location
import org.skyfaced.network.model.filter.CharacterFilter
import org.skyfaced.network.model.filter.EpisodeFilter
import org.skyfaced.network.model.filter.LocationFilter
import org.skyfaced.network.model.filter.asMap
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

private const val BASE_URL = "https://rickandmortyapi.com/api"

class RMNetwork @Inject constructor(
    json: Json
) : RMNetworkDataSource {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor { message ->
                logcat("OkHttp") { message }
            }.setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val api = retrofit.create<RMApi>()

    override suspend fun getAllCharacters(
        page: Int,
        filter: CharacterFilter
    ): InfoWrapper<Character> = api.getAllCharacters(page, filter.asMap())

    override suspend fun getSingleCharacter(id: Int): Character = api.getSingleCharacter(id)

    override suspend fun getMultipleCharacters(ids: List<String>): List<Character> =
        api.getMultipleCharacters(ids.joinToString(separator = ","))

    override suspend fun getAllLocations(
        page: Int,
        filters: LocationFilter
    ): InfoWrapper<Location> = api.getAllLocations(page, filters.asMap())

    override suspend fun getSingleLocation(id: Int): Location = api.getSingleLocation(id)

    override suspend fun getMultipleLocations(ids: List<String>): List<Location> =
        api.getMultipleLocations(ids.joinToString(separator = ","))

    override suspend fun getAllEpisodes(page: Int, filters: EpisodeFilter): InfoWrapper<Episode> =
        api.getAllEpisodes(page, filters.asMap())

    override suspend fun getSingleEpisode(id: Int): Episode = api.getSingleEpisode(id)

    override suspend fun getMultipleEpisodes(ids: List<String>): List<Episode> =
        api.getMultipleEpisodes(ids.joinToString(separator = ","))
}