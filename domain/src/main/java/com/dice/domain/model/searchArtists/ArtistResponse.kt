package com.dice.domain.model.searchArtists

data class ArtistsResponse(
    val count: Int,
    val offset: Int,
    val artists: List<Artist>
)

data class Artist(
    val id: String,
    val name: String,
    val gender: Gender,
    val score: Int,
    val area: Area?
)

data class Area(
    val id: String,
    val type: String,
    val name: String
)

enum class Gender(type: String) {
    MALE("male"),
    FEMALE("female")
}