package com.dice.feature.artist.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dice.domain.model.common.MusicBrainzException
import com.dice.feature.artist.search.EmptyView
import com.dice.feature.artist.search.ErrorView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArtistDetailsScreen(
    navController: NavHostController,
    artistId: String?,
    viewModel: ArtistDetailsViewModel = hiltViewModel()
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val state = viewModel.state

    LaunchedEffect(artistId) {
        Log.d("calling artist details", "Launched effect")
        viewModel.getArtistDetails()
    }

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            DetailsTopBar()
        },
        content = {
            if(viewModel.state.isLoading) {
                LoadingIndicator()
            }
            
            state.error?.let {
                when (it) {
                    is MusicBrainzException.Empty -> { /*Handle empty state*/
                        EmptyView("")
                    }
                    is MusicBrainzException.ServerError -> { /*Handle server error state*/
                        ErrorView(error = MusicBrainzException.ServerError(it.code))
                    }
                    is MusicBrainzException.Exception -> { /*Handle exception state*/
                        ErrorView(error = MusicBrainzException.Exception(it.throwable))
                    }
                    else -> Unit
                }
            }
            
            state.artistsResponse?.let { 
                Text(text = it.artist.name + "\n Recordings = ${it.recordings.size}")
            }

        }
    )
}

@Composable
fun DetailsTopBar() {
    TopAppBar(
        backgroundColor = Color.DarkGray,
    ) {
        Text(text = "Artist Details", color = Color.White)
    }
}

@Composable
fun LoadingIndicator() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentSize()
        )
    }
}

@Composable
fun ArtistDetails() {

}

@Composable
fun Recordings() {

}

@Composable
@Preview
fun ArtistDetailsPreview() {

}

@Composable
@Preview
fun RecordingsPreview() {

}

@Composable
@Preview
fun LoadingIndicatorPreview() {

}