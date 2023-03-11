package com.dice.domain.usecase

import com.dice.domain.model.artistDetails.ArtistDetailsResponse
import com.dice.domain.model.common.Result
import kotlinx.coroutines.flow.Flow

interface ArtistDetailsUseCase {

    suspend fun getArtistDetails(
        artistId: String,
        includeFields: List<String>
    ): Flow<Result<ArtistDetailsResponse>>
}