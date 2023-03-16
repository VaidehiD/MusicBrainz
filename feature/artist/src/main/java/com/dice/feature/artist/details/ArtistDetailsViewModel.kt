package com.dice.feature.artist.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dice.domain.model.artistDetails.ArtistDetailsResponse
import com.dice.domain.model.common.MusicBrainzException
import com.dice.domain.model.common.Result
import com.dice.domain.usecase.ArtistDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ArtistDetailsState(
    val artistID: String = "",
    val error: MusicBrainzException? = null,
    val isLoading: Boolean = false,
    val artistsResponse: ArtistDetailsResponse? = null
)

@HiltViewModel
class ArtistDetailsViewModel @Inject constructor(val useCase: ArtistDetailsUseCase) : ViewModel() {

    var state by mutableStateOf(ArtistDetailsState())

    suspend fun getArtistDetails() {
        viewModelScope.launch {
            useCase(state.artistID).catch {
                state = state.copy(error = MusicBrainzException.Exception(it), isLoading = false)
            }.onEmpty {
                state = state.copy(error = MusicBrainzException.Empty, isLoading = false)
            }.onEach {
                when(it) {
                    is Result.Success -> {
                        state = state.copy(artistsResponse = it.data, isLoading = false)
                    }
                    is Result.Failure -> {
                        state = state.copy(error = it.data, isLoading = false)
                    }
                    else -> { /* Do Nothing */ }
                }
            }
        }

    }
}