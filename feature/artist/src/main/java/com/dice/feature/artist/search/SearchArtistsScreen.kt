package com.dice.feature.artist.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dice.domain.model.common.MusicBrainzException
import com.dice.domain.model.searchArtists.Area
import com.dice.domain.model.searchArtists.Artist
import com.dice.features.R

@Composable
fun SearchArtistsScreen(
    navController: NavHostController,
    viewModel: SearchArtistsViewModel = hiltViewModel()
) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val state = viewModel.state

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            HomeTopBar()
        },
        content = { paddingValues ->

            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(all = paddingValues.calculateBottomPadding())
            ) {

                SearchBar(state = state, updateSearchKeyword = {
                    viewModel.updateSearchKeyword(it)
                }) {
                    viewModel.resetSearch()
                    viewModel.searchArtists()
                }

                if (state.artists.isNotEmpty()) {
                    ArtistList(state = state) {
                        viewModel.searchArtists()
                    }
                }

                state.error?.let {
                    when (it) {
                        is MusicBrainzException.Empty -> { /*Handle empty state*/
                            EmptyView(state.searchKeyword)
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

                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .wrapContentSize()
                    )
                }
            }
        })
}

@Composable
fun HomeTopBar() {
    TopAppBar(
        backgroundColor = Color.DarkGray,
    ) {
        Text(text = stringResource(id = R.string.home_screen_title), color = Color.White)
    }
}

@Composable
fun SearchBar(
    state: SearchArtistsState,
    updateSearchKeyword: (String) -> Unit,
    searchArtist: () -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = MaterialTheme.shapes.small),
        value = state.searchKeyword,
        singleLine = true,
        onValueChange = { updateSearchKeyword(it) },
        trailingIcon = {
            IconButton(onClick = {
                searchArtist()
            }) {
                Icon(
                    painterResource(id = R.drawable.outline_search_24),
                    contentDescription = stringResource(R.string.content_desc_search_icon)
                )
            }
        })
}

@Composable
fun ArtistList(state: SearchArtistsState, searchArtist: () -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.artists.size) { index ->
            val artist = state.artists[index]
            if (index >= state.artists.size - 1 && !state.isEndOfList && !state.isLoading) {
                searchArtist()
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = artist.name,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = artist.score.toString(),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(state = SearchArtistsState(searchKeyword = "abc"), updateSearchKeyword = {}) {}
}

@Preview
@Composable
fun ArtistListPreview() {
    ArtistList(
        state = SearchArtistsState(
            artists = listOf(
                Artist(
                    id = "id1",
                    name = "Tom",
                    score = 90,
                    area = Area(
                        id = "area1",
                        type = "city",
                        name = "SF"
                    )
                ),
                Artist(
                    id = "id2",
                    name = "Jerry",
                    score = 95,
                    area = Area(
                        id = "area1",
                        type = "city",
                        name = "London"
                    )
                )
            )
        )
    ) {}
}