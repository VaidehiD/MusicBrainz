package com.dice.feature.artist.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dice.domain.model.common.MusicBrainzException
import com.dice.features.R

@Composable
fun ErrorView(error: MusicBrainzException) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = stringResource(
                R.string.error_screen_message, when (error) {
                    is MusicBrainzException.ServerError -> error.code
                    is MusicBrainzException.Exception -> error.toString()
                    else -> stringResource(id = R.string.no_artists_error)
                }
            )
        )
    }
}

@Composable
@Preview
fun ServerErrorPreview() {
    ErrorView(error = MusicBrainzException.ServerError(501))
}

@Composable
@Preview
fun ExceptionPreview() {
    ErrorView(error = MusicBrainzException.Exception(IllegalArgumentException()))
}