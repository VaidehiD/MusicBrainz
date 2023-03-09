package com.dice.data.source.remote.artists.details

import com.dice.data.api.DetailApi
import com.dice.data.entity.ArtistDetailsResponse
import com.dice.data.utils.parseResult
import com.dice.domain.model.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArtistDetailsRemoteSourceImpl(private val artistDetailApi: DetailApi):
    ArtistDetailsRemoteSource {

    override suspend fun getArtistDetails(artistId: String, includeFields: String):
            Flow<Result<ArtistDetailsResponse>> = flow {
        emit(artistDetailApi.getArtistDetails(artistId, includeFields).parseResult())
    }
}