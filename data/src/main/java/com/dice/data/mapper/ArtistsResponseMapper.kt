package com.dice.data.mapper

import com.dice.domain.model.searchArtists.Area
import com.dice.domain.model.searchArtists.Artist
import com.dice.domain.model.searchArtists.ArtistsResponse
import com.dice.data.entity.Area as AreaEntity
import com.dice.data.entity.Artist as ArtistEntity
import com.dice.data.entity.ArtistsResponse as ArtistsResponseEntity

class ArtistsResponseMapper {

    fun artistsResponseMapper(searchArtistsResponse: ArtistsResponseEntity) =
        ArtistsResponse(
            count = searchArtistsResponse.count,
            offset = searchArtistsResponse.offset,
            artists = mapArtists(searchArtistsResponse.artists)
        )

    internal fun mapArtists(artists: List<ArtistEntity>) =
        artists.map {
            Artist(
                id = it.id,
                name = it.name,
                score = it.score,
                area = it.area?.let { area -> mapArea(area) })
        }

    private fun mapArea(area: AreaEntity) =
        Area(id = area.id, name = area.name, type = area.type)
}