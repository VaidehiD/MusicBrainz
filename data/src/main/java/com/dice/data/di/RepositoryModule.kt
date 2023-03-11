package com.dice.data.di

import com.dice.data.mapper.ArtistDetailsMapper
import com.dice.data.mapper.ArtistsResponseMapper
import com.dice.data.repository.ArtistDetailsRepositoryImpl
import com.dice.data.repository.SearchArtistsRepositoryImpl
import com.dice.data.source.remote.artists.SearchArtistsRemoteSource
import com.dice.data.source.remote.artists.details.ArtistDetailsRemoteSource
import com.dice.domain.repository.ArtistDetailsRepository
import com.dice.domain.repository.SearchArtistsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton


@Module
@InstallIn(Singleton::class)
object RepositoryModule {

    @Provides
    fun providesSearchArtistRepository(
        remoteSource: SearchArtistsRemoteSource,
        mapper: ArtistsResponseMapper
    ): SearchArtistsRepository =
        SearchArtistsRepositoryImpl(remoteSource, mapper)

    @Provides
    fun providesArtistDetailsRepository(
        remoteSource: ArtistDetailsRemoteSource,
        mapper: ArtistDetailsMapper
    ): ArtistDetailsRepository =
        ArtistDetailsRepositoryImpl(remoteSource, mapper)

    @Provides
    fun providesArtistMapper() = ArtistsResponseMapper()

    @Provides
    fun providesArtistDetailsMapper(artistMapper: ArtistsResponseMapper) =
        ArtistDetailsMapper(artistMapper)
}