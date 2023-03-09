package com.dice.domain.model.common

sealed class MusicBrainzException {
    object Empty: MusicBrainzException()
    data class ServerError(val code: Int): MusicBrainzException()
    data class Exception(val throwable: Throwable): MusicBrainzException()
}