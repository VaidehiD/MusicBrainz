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
import com.dice.features.R

@Composable
fun EmptyView(keyword: String) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = stringResource(R.string.empty_screen_message, keyword)
        )
    }
}

@Composable
@Preview
fun EmptyViewPreview() {
    EmptyView(keyword = "abc")
}