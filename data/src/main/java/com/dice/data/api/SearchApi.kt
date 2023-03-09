package com.dice.data.api

import com.dice.data.entity.ArtistsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("artist/query=artist:{keyword}&offset={offset}&fmt=json")
    suspend fun searchArtists(
        @Query("keyword") keyword: String, @Query("offset") offset: Int
    ): Response<ArtistsResponse>

}