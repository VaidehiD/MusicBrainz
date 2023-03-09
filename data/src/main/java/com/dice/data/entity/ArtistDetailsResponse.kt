package com.dice.data.entity

import java.io.Serializable

data class ArtistDetailsResponse(
    val artist: Artist,
    val recordings: List<Recording>
) : Serializable

data class Recording(
    val id: String,
    val title: String,
    val length: Double,
    val isVideo: Boolean
) : Serializable
