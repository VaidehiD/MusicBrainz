package com.dice.feature.artist.utils

interface Paginator<Int, Artist> {
    suspend fun loadNextItems(keyword: String)
    fun reset()
}