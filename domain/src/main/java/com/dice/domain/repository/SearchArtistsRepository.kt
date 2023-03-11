package com.dice.domain.repository

import com.dice.domain.model.searchArtists.ArtistsResponse
import com.dice.domain.model.common.Result
import kotlinx.coroutines.flow.Flow

interface SearchArtistsRepository {
    suspend fun searchArtists(keyword: String, offset: Int): Flow<Result<ArtistsResponse>>
}