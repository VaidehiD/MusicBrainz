package com.dice.data.source.remote.artists

import com.dice.data.entity.ArtistsResponse
import com.dice.domain.model.common.Result
import kotlinx.coroutines.flow.Flow

interface SearchArtistsRemoteSource {
    suspend fun searchArtists(keyword: String, offset: Int): Flow<Result<ArtistsResponse>>
}