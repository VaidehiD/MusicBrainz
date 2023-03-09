package com.dice.domain.model.common

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T): Result<T>()
    data class Failure(val data: MusicBrainzException): Result<Nothing>()
}