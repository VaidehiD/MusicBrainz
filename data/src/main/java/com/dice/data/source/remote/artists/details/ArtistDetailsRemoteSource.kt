package com.dice.data.source.remote.artists.details

import com.dice.data.entity.ArtistDetailsResponse
import com.dice.domain.model.common.Result
import kotlinx.coroutines.flow.Flow

interface ArtistDetailsRemoteSource {

    suspend fun getArtistDetails(artistId: String, includeFields: String):
            Flow<Result<ArtistDetailsResponse>>
}