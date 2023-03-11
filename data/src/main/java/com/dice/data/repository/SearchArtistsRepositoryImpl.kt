package com.dice.data.repository

import com.dice.data.mapper.ArtistsResponseMapper
import com.dice.data.source.remote.artists.SearchArtistsRemoteSource
import com.dice.domain.model.searchArtists.ArtistsResponse
import com.dice.domain.model.common.Result
import com.dice.domain.repository.SearchArtistsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchArtistsRepositoryImpl(
    private val dataSource: SearchArtistsRemoteSource,
    private val mapper: ArtistsResponseMapper
): SearchArtistsRepository {
    override suspend fun searchArtists(
        keyword: String,
        offset: Int
    ): Flow<Result<ArtistsResponse>> {
        return dataSource.searchArtists(keyword, offset).map {
            when (it) {
                is Result.Success -> Result.Success(mapper.artistsResponseMapper(it.data))
                is Result.Failure -> Result.Failure(it.data)
            }
        }
    }
}