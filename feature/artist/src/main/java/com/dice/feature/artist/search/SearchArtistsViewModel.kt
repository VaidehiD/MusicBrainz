package com.dice.feature.artist.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dice.domain.model.common.MusicBrainzException
import com.dice.domain.model.searchArtists.Artist
import com.dice.domain.usecase.SearchArtistsUseCase
import com.dice.feature.artist.utils.DefaultPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchArtistsState(
    val isLoading: Boolean = false,
    val searchKeyword: String = "",
    val artists: List<Artist> = emptyList(),
    val artistsCount: Int = 0,
    val error: MusicBrainzException? = null,
    val isEndOfList: Boolean = false,
    val offset: Int = 1
)

@HiltViewModel
class SearchArtistsViewModel @Inject constructor(useCase: SearchArtistsUseCase) : ViewModel() {

    var state by mutableStateOf(SearchArtistsState())

    private val paginator = DefaultPaginator<Int, Artist>(
        initialOffset = state.offset,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { keyword, nextOffset ->
            useCase(keyword, offset = nextOffset).first()
        },
        getNextOffset = {
            state.offset
        },
        onError = {
            state = state.copy(error = it, isLoading = false)
        },
        onSuccess = {
            state = state.copy(
                error = null,
                artists = state.artists + it.artists,
                artistsCount = it.count,
                offset = state.offset + 1,
                isEndOfList = it.count <= state.artists.size,
                isLoading = false
            )
        }
    )

    fun updateSearchKeyword(keyWord: String) {
        state = state.copy(searchKeyword = keyWord)
    }

    fun searchArtists() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            paginator.loadNextItems(state.searchKeyword)
        }
    }

    fun resetSearch() {
        state = state.copy(
            artists = emptyList(),
            artistsCount = 0,
            isEndOfList = true,
            offset = 1
        )
    }
}