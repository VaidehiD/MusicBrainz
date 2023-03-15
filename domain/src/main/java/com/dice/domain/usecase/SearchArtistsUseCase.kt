package com.dice.domain.usecase

import com.dice.domain.model.common.Result
import com.dice.domain.model.searchArtists.ArtistsResponse
import com.dice.domain.repository.SearchArtistsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchArtistsUseCase @Inject constructor(private val repository: SearchArtistsRepository) {
    suspend operator fun invoke(
        keyword: String,
        offset: Int
    ): Flow<Result<ArtistsResponse>> {
        return repository.searchArtists(keyword, offset)
    }
}