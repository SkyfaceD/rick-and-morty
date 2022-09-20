package org.skyfaced.network

import logcat.logcat
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.skyfaced.network.model.Character
import org.skyfaced.network.model.Episode
import org.skyfaced.network.model.InfoWrapper
import org.skyfaced.network.model.Location
import retrofit2.Retrofit
import retrofit2.create

private const val BASE_URL = "https://rickandmortyapi.com/api"

class RMNetwork : RMApi {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor { message ->
                logcat("OkHttp") { message }
            }.setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    private val api = retrofit.create<RMApi>()

    override suspend fun getAllCharacters(filters: Map<String, String>): InfoWrapper<Character> =
        api.getAllCharacters(filters)

    override suspend fun getSingleCharacter(id: Int): Character = api.getSingleCharacter(id)

    override suspend fun getMultipleCharacters(ids: String): List<Character> =
        api.getMultipleCharacters(ids)

    override suspend fun getAllLocations(filters: Map<String, String>): InfoWrapper<Location> =
        api.getAllLocations(filters)

    override suspend fun getSingleLocation(id: Int): Location = api.getSingleLocation(id)

    override suspend fun getMultipleLocations(ids: String): List<Location> =
        api.getMultipleLocations(ids)

    override suspend fun getAllEpisodes(filters: Map<String, String>): InfoWrapper<Episode> =
        api.getAllEpisodes(filters)

    override suspend fun getSingleEpisode(id: Int): Episode = api.getSingleEpisode(id)

    override suspend fun getMultipleEpisodes(ids: String): List<Episode> =
        api.getMultipleEpisodes(ids)
}