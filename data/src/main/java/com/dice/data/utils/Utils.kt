package com.dice.data.utils

import com.dice.domain.model.common.MusicBrainzException
import com.dice.domain.model.common.Result
import com.dice.domain.model.common.Result.Failure
import com.dice.domain.model.common.Result.Success
import retrofit2.Response

fun <T : Any> Response<T>.parseResult(): Result<T> {
    return if (this.isSuccessful) {
        val body = body()
        if (body == null) {
            Failure(MusicBrainzException.Empty)
        } else {
            Success(body)
        }
    } else {
        Failure(MusicBrainzException.ServerError(code()))
    }
}