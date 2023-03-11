package com.dice.domain.usecase

import com.dice.domain.model.common.Result
import com.dice.domain.model.searchArtists.ArtistsResponse
import kotlinx.coroutines.flow.Flow

interface SearchArtistsUseCase {
    suspend fun searchArtists(keyword: String, offset: Int): Flow<Result<ArtistsResponse>>
}