package com.dice.feature.artist.utils

import com.dice.domain.model.common.MusicBrainzException
import com.dice.domain.model.common.Result
import com.dice.domain.model.searchArtists.ArtistsResponse

class DefaultPaginator<Int, Artist>(
    private val initialOffset: Int,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (keyword: String, nextOffset: Int) -> Result<ArtistsResponse>,
    private inline val getNextOffset: suspend () -> Int,
    private inline val onError: suspend(MusicBrainzException?) -> Unit,
    private inline val onSuccess: suspend (artists: ArtistsResponse) -> Unit
): Paginator<Int, Artist> {

    private var offset = initialOffset
    private var isMakingRequest = false

    override suspend fun loadNextItems(keyword: String) {
        if(isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(keyword, offset)
        isMakingRequest = false

        when(result) {
            is Result.Failure -> {
                onError(result.data)
                onLoadUpdated(false)
                return
            }
            is Result.Success -> {
                onSuccess(result.data)
                onLoadUpdated(false)
                offset = getNextOffset()
            }
        }
    }

    override fun reset() {
        offset = initialOffset
    }
}