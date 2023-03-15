package com.dice.domain.usecase

import com.dice.domain.model.artistDetails.ArtistDetailsResponse
import com.dice.domain.model.common.Result
import com.dice.domain.repository.ArtistDetailsRepository
import kotlinx.coroutines.flow.Flow

class ArtistDetailsUseCase(private val artistDetailsRepository: ArtistDetailsRepository) {

    suspend operator fun invoke(
        artistId: String,
        includeFields: List<String>
    ): Flow<Result<ArtistDetailsResponse>> {
        return artistDetailsRepository.getArtistDetails(artistId, includeFields)
    }
}