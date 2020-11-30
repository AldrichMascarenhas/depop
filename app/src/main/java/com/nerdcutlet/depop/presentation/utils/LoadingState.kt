package com.nerdcutlet.depop.presentation.utils

enum class LoadingState {
    Ready,
    Loading,
    Error
}

fun List<LoadingState>.getCombinedLoadingState(): LoadingState {
    return when {
        any { it == LoadingState.Error } -> LoadingState.Error
        any { it == LoadingState.Loading } -> LoadingState.Loading
        else -> LoadingState.Ready
    }
}
