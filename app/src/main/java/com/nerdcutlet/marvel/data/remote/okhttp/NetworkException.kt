package com.nerdcutlet.marvel.data.remote.okhttp

import java.io.IOException

class NetworkException : IOException {
    constructor()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)
}
