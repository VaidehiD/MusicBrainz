package com.dice.data.repository

import com.dice.data.mapper.ArtistDetailsMapper
import com.dice.data.source.remote.artists.details.ArtistDetailsRemoteSource
import com.dice.domain.model.artistDetails.ArtistDetailsResponse
import com.dice.domain.model.common.Result
import com.dice.domain.repository.ArtistDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArtistDetailsRepositoryImpl(
    private val dataSource: ArtistDetailsRemoteSource,
    private val mapper: ArtistDetailsMapper
) : ArtistDetailsRepository {

    override suspend fun getArtistDetails(
        artistId: String,
        includeFields: List<String>
    ): Flow<Result<ArtistDetailsResponse>> {
        return dataSource.getArtistDetails(artistId, mapper.includeFieldsRequest(includeFields))
            .map {
                when (it) {
                    is Result.Success -> Result.Success(mapper.artistDetailsResponseMapper(it.data))
                    is Result.Failure -> Result.Failure(it.data)
                }
            }
    }
}