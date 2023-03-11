package com.dice.domain.usecase

import com.dice.domain.model.common.Result
import com.dice.domain.model.searchArtists.ArtistsResponse
import com.dice.domain.repository.SearchArtistsRepository
import kotlinx.coroutines.flow.Flow

class SearchArtistsUseCaseImpl(private val repository: SearchArtistsRepository) :
    SearchArtistsUseCase {
    override suspend fun searchArtists(
        keyword: String,
        offset: Int
    ): Flow<Result<ArtistsResponse>> {
        return repository.searchArtists(keyword, offset)
    }
}