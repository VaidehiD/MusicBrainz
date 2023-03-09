package com.dice.data.entity

import java.io.Serializable

data class ArtistsResponse(
    val count: Int,
    val offset: Int,
    val artists: List<Artist>
) : Serializable

data class Artist(
    val id: String,
    val name: String,
    val gender: String,
    val score: Int,
    val area: Area?
) : Serializable

data class Area(
    val id: String,
    val type: String,
    val name: String
) : Serializable
