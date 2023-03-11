package com.dice.domain.usecase

import com.dice.domain.model.artistDetails.ArtistDetailsResponse
import com.dice.domain.model.common.Result
import com.dice.domain.repository.ArtistDetailsRepository
import kotlinx.coroutines.flow.Flow

class ArtistDetailsUseCaseImpl(private val artistDetailsRepository: ArtistDetailsRepository) :
    ArtistDetailsUseCase {

    override suspend fun getArtistDetails(
        artistId: String,
        includeFields: List<String>
    ): Flow<Result<ArtistDetailsResponse>> {
        return artistDetailsRepository.getArtistDetails(artistId, includeFields)
    }
}