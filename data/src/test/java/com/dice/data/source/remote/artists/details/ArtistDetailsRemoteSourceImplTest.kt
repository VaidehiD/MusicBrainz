package com.dice.data.source.remote.artists.details

import com.dice.data.api.DetailApi
import com.dice.data.source.remote.artists.mock.artist1
import com.dice.data.source.remote.artists.mock.artist2
import com.dice.data.source.remote.artists.mock.artistDetailsResponse
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

internal class ArtistDetailsRemoteSourceImplTest {

    @Mock
    private lateinit var api: DetailApi

    private lateinit var remoteSource: ArtistDetailsRemoteSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteSource = ArtistDetailsRemoteSourceImpl(api)
    }

    @Test
    fun `when getArtistDetails has no response should return Empty Result`() = runTest {
        Mockito.`when`(api.getArtistDetails(Mockito.anyString(), Mockito.anyString())).thenReturn(
            Response.success(null)
        )
        val response = remoteSource.getArtistDetails(artist1.id, "recordings").first()
        Assert.assertEquals(Result.Failure(MusicBrainzException.Empty), response)
    }

    @Test
    fun `when getArtistDetails has successful response should return Success`() = runTest {
        Mockito.`when`(api.getArtistDetails(Mockito.anyString(), Mockito.anyString())).thenReturn(
            Response.success(artistDetailsResponse)
        )
        val response = remoteSource.getArtistDetails(artist1.id, "recordings").first()
        Assert.assertEquals(Result.Success(artistDetailsResponse), response)
    }

    @Test
    fun `when getArtistDetails fails it should return Failure`() = runTest {
        Mockito.`when`(api.getArtistDetails(Mockito.anyString(), Mockito.anyString())).thenReturn(
            Response.error(403, ResponseBody.create(null, "error"))
        )
        val response = remoteSource.getArtistDetails(artist2.id, "recordings").first()
        Assert.assertEquals(Result.Failure(MusicBrainzException.ServerError(403)), response)
    }
}