package com.dice.data.di

import com.dice.data.mapper.ArtistDetailsMapper
import com.dice.data.mapper.ArtistsResponseMapper
import com.dice.data.mapper.SearchArtistsRequestMapper
import com.dice.data.repository.ArtistDetailsRepositoryImpl
import com.dice.data.repository.SearchArtistsRepositoryImpl
import com.dice.data.source.remote.artists.SearchArtistsRemoteSource
import com.dice.data.source.remote.artists.details.ArtistDetailsRemoteSource
import com.dice.domain.repository.ArtistDetailsRepository
import com.dice.domain.repository.SearchArtistsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesSearchArtistRepository(
        remoteSource: SearchArtistsRemoteSource,
        mapper: ArtistsResponseMapper,
        requestMapper: SearchArtistsRequestMapper
    ): SearchArtistsRepository =
        SearchArtistsRepositoryImpl(remoteSource, mapper, requestMapper)

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

    @Provides
    fun providesSearchArtistRequestMapper() = SearchArtistsRequestMapper()
}