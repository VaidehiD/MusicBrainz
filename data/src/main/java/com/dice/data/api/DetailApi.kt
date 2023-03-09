package com.dice.data.api

import com.dice.data.entity.ArtistDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailApi {

    @GET("artist/{artist_id}?inc={inc}&fmt=json")
    suspend fun searchArtists(
        @Path("artist_id") keyword: String, @Query("inc") includeFields: String
    ): Response<ArtistDetailsResponse>

}