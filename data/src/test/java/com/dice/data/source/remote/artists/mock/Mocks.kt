package com.dice.data.source.remote.artists.mock

import com.dice.data.entity.*

val area1 = Area(
    id = "1f40c6e1-47ba-4e35-996f-fe6ee5840e62",
    type = "City",
    name = "Los Angeles",
)

val area2 = area1.copy(id = "489ce91b-6658-3307-9877-795b68554c98")

val artist1 = Artist(
    id = "f2154126-dd68-4f02-8821-0d4508d87a57",
    name = "Fred Sanders",
    score = 93,
    area = area1
)

val artist2 = artist1.copy(id = "36d3d30a-839d-3eda-8cb3-29be4384e4a9", area = area2)

val artistsResponse = ArtistsResponse(
    count = 2,
    offset = 1,
    artists = listOf(artist1, artist2))

val recording1 = Recording(
    id = "4d48e907-5c0d-4b9d-acbb-5e6d752d9c28",
    title = "1 - Taking the Red Pill",
    length = 1801665.toDouble(),
    isVideo = false
)

val recording2 = recording1.copy(id = "53687da-9886-4654-8550-7ee4913e0e26")

val artistDetailsResponse = ArtistDetailsResponse(
    artist = artist1,
    recordings = listOf(recording1, recording2)
)