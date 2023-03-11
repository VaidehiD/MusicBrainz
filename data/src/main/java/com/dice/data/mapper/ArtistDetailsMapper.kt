package com.dice.data.mapper

import com.dice.domain.model.artistDetails.ArtistDetailsResponse
import com.dice.domain.model.artistDetails.Recording
import com.dice.data.entity.ArtistDetailsResponse as ArtistDetailsResponseEntity
import com.dice.data.entity.Recording as RecordingEntity

class ArtistDetailsMapper(private val artistResponseMapper: ArtistsResponseMapper) {

    internal fun artistDetailsResponseMapper(artistDetails: ArtistDetailsResponseEntity) =
        ArtistDetailsResponse(
            artist = artistResponseMapper.mapArtists(listOf(artistDetails.artist)).first(),
            recordings = mapRecordings(artistDetails.recordings)
        )

    internal fun includeFieldsRequest(includeFields: List<String>) = includeFields
        .joinToString("+")

    private fun mapRecordings(recordings: List<RecordingEntity>): List<Recording> =
        recordings.map {
            Recording(id = it.id, title = it.title, length = it.length, isVideo = it.isVideo)
        }
}