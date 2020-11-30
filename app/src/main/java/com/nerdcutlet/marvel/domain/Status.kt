package com.nerdcutlet.marvel.domain

sealed class Status<out T : Any> {
    data class Success<T : Any>(val data: T) : Status<T>()
    data class Error(val e: Throwable) : Status<Nothing>()
    object Loading : Status<Nothing>()
}
