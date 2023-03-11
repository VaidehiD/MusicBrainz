package com.dice.data.di

import com.dice.data.api.DetailApi
import com.dice.data.api.SearchApi
import com.dice.data.source.remote.artists.SearchArtistsRemoteSource
import com.dice.data.source.remote.artists.SearchArtistsRemoteSourceImpl
import com.dice.data.source.remote.artists.details.ArtistDetailsRemoteSource
import com.dice.data.source.remote.artists.details.ArtistDetailsRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    fun provideSearchArtistsRemoteSource(searchApi: SearchApi): SearchArtistsRemoteSource =
        SearchArtistsRemoteSourceImpl(searchApi)

    @Provides
    fun provideArtistDetailsRemoteSource(detailApi: DetailApi): ArtistDetailsRemoteSource =
        ArtistDetailsRemoteSourceImpl(detailApi)
}