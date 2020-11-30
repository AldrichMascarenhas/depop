package com.nerdcutlet.marvel.presentation.herodetail

import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.presentation.utils.LoadingState
import com.nerdcutlet.marvel.presentation.utils.getCombinedLoadingState

data class HeroDetailState(
    val loadingHeroState: LoadingState = LoadingState.Loading,
    val isHeroInSquadLoadingState: LoadingState = LoadingState.Loading,
    val isLiked: Boolean = false,
    val hero: HeroDomainModel? = null
) {

    val screenState: LoadingState
        get() = listOf(
            loadingHeroState,
            isHeroInSquadLoadingState
        ).getCombinedLoadingState()
}
