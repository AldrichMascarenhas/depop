package com.nerdcutlet.marvel.presentation.herolist

import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.presentation.utils.LoadingState
import com.nerdcutlet.marvel.presentation.utils.getCombinedLoadingState

data class HeroListState(
    val heroes: List<HeroDomainModel> = emptyList(),
    val loadingHeroes: LoadingState = LoadingState.Loading
) {
    val screenState: LoadingState
        get() = listOf(
            loadingHeroes
        ).getCombinedLoadingState()
}
