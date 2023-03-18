package com.ma.development.booksapp.domain.utils

sealed class Result<T> {

    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val exception: Throwable) : Result<T>()

}

inline fun <T> Result<T>.onSuccess(
    block: (T) -> Unit
): Result<T> = if (this is Result.Success) also { block(data) } else this

inline fun <T> Result<T>.onFailure(
    block: (Throwable) -> Unit
): Result<T> = if (this is Result.Failure) also { block(exception) } else this