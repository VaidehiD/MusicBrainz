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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dice.domain.model.common.MusicBrainzException
import com.dice.features.R

@Composable
fun SearchArtistsScreen(navController: NavHostController, viewModel: SearchArtistsViewModel = hiltViewModel()) {

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

                // Todo: Move to a separate composable

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = MaterialTheme.shapes.small),
                    value = state.searchKeyword,
                    singleLine = true,
                    onValueChange = { viewModel.updateSearchKeyword(it) },
                    trailingIcon = {
                            IconButton(onClick = {
                                viewModel.resetSearch()
                                viewModel.searchArtists()
                            }) {
                                Icon(
                                    painterResource(id = R.drawable.outline_search_24),
                                    contentDescription = stringResource(R.string.content_desc_search_icon)
                                )
                            }

                    })


                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .wrapContentSize()
                    )
                } else if (state.error != null) { // Todo: remove the condition and move the error conditions at the end.

                    when (state.error) {
                        is MusicBrainzException.Empty -> { /*Handle empty state*/
                            EmptyView(state.searchKeyword)
                        }
                        is MusicBrainzException.ServerError -> { /*Handle server error state*/
                            ErrorView(error = MusicBrainzException.ServerError(state.error.code))
                        }
                        is MusicBrainzException.Exception -> { /*Handle exception state*/
                            ErrorView(error = MusicBrainzException.Exception(state.error.throwable))
                        }
                        else -> Unit
                    }
                } else {

                    // Todo: Move to a separate composable
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.artists.size) { index ->
                            val artist = state.artists[index]
                            if (index >= state.artists.size - 1 && !state.isEndOfList && !state.isLoading) {
                                viewModel.searchArtists()
                            }
                            Card(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(8.dp)) {

                                Column(Modifier.fillMaxWidth().wrapContentHeight()) {
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
