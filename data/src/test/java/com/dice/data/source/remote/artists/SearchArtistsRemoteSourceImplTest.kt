package com.dice.data.source.remote.artists

import com.dice.data.api.SearchApi
import com.dice.data.source.remote.artists.mock.artistsResponse
import com.dice.domain.model.common.MusicBrainzException
import com.dice.domain.model.common.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

internal class SearchArtistsRemoteSourceImplTest {

    @Mock
    private lateinit var api: SearchApi

    private lateinit var remoteSource: SearchArtistsRemoteSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteSource = SearchArtistsRemoteSourceImpl(api)
    }

    @Test
    fun `when searchArtists has no response should return Empty Result`() = runTest {
        Mockito.`when`(api.searchArtists(Mockito.anyString(), Mockito.anyInt())).thenReturn(
            Response.success(null)
        )
        val response = remoteSource.searchArtists("Tom", 1).first()
        Assert.assertEquals(Result.Failure(MusicBrainzException.Empty), response)
    }

    @Test
    fun `when searchArtists has successful response should return Success`() = runTest {
        Mockito.`when`(api.searchArtists(Mockito.anyString(), Mockito.anyInt())).thenReturn(
            Response.success(artistsResponse)
        )
        val response = remoteSource.searchArtists("Fred", 1).first()
        Assert.assertEquals(Result.Success(artistsResponse), response)
    }

    @Test
    fun `when searchArtists fails it should return Failure`() = runTest {
        Mockito.`when`(api.searchArtists(Mockito.anyString(), Mockito.anyInt())).thenReturn(
            Response.error(403, ResponseBody.create(null, "error"))
        )
        val response = remoteSource.searchArtists("Fred", 1).first()
        Assert.assertEquals(Result.Failure(MusicBrainzException.ServerError(403)), response)
    }
}