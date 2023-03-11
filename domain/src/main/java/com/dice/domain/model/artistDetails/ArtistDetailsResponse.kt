package com.dice.domain.model.artistDetails

import com.dice.domain.model.searchArtists.Artist

data class ArtistDetailsResponse(
    val artist: Artist,
    val recordings: List<Recording>
)

data class Recording(
    val id: String,
    val title: String,
    val length: Double,
    val isVideo: Boolean
)
