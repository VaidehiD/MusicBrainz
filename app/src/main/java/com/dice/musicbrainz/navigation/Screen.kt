package com.dice.musicbrainz.navigation

sealed class Screen(val route: String) {
    object SearchArtistsScreen : Screen("search_artists")
    object ArtistDetailsScreen : Screen("artist_details_screen/{artist_id}") {
        fun passArtistID(artistId: String) = "artist_details_screen/$artistId"
    }
}
