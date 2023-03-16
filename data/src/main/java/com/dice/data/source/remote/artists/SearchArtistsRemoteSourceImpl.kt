package com.dice.data.source.remote.artists

import com.dice.data.api.SearchApi
import com.dice.data.entity.ArtistsResponse
import com.dice.data.utils.parseResult
import com.dice.domain.model.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchArtistsRemoteSourceImpl(private val searchApi: SearchApi):
    SearchArtistsRemoteSource {

    override suspend fun searchArtists(
        keyword: String,
        offset: Int
    ): Flow<Result<ArtistsResponse>> = flow {
        emit(searchApi.searchArtists(keyword, offset).parseResult())
    }
}