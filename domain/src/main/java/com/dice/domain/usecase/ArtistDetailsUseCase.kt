package com.dice.domain.usecase

import com.dice.domain.model.artistDetails.ArtistDetailsIncludeFields
import com.dice.domain.model.artistDetails.ArtistDetailsResponse
import com.dice.domain.model.common.Result
import com.dice.domain.repository.ArtistDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistDetailsUseCase @Inject constructor(private val artistDetailsRepository: ArtistDetailsRepository) {

    suspend operator fun invoke(
        artistId: String
    ): Flow<Result<ArtistDetailsResponse>> {
        return artistDetailsRepository.getArtistDetails(artistId, includeFields = listOf(
            ArtistDetailsIncludeFields.RECORDING.name.lowercase()
        ))
    }
}