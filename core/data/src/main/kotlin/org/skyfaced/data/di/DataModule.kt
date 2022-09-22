package org.skyfaced.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.skyfaced.data.repository.CharacterRepository
import org.skyfaced.data.repository.CharacterRepositoryImpl
import org.skyfaced.data.repository.EpisodeRepository
import org.skyfaced.data.repository.EpisodeRepositoryImpl
import org.skyfaced.data.repository.LocationRepository
import org.skyfaced.data.repository.LocationRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsCharacterRepository(
        characterRepository: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    fun bindsLocationRepository(
        locationRepository: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    fun bindsEpisodeRepository(
        episodeRepository: EpisodeRepositoryImpl
    ): EpisodeRepository
}