package com.nerdcutlet.depop.presentation.herolist

import com.nerdcutlet.depop.domain.model.ProductDomainModel
import com.nerdcutlet.depop.presentation.utils.LoadingState
import com.nerdcutlet.depop.presentation.utils.getCombinedLoadingState

data class HeroListState(
    val products: List<ProductDomainModel> = emptyList(),
    val loadingHeroes: LoadingState = LoadingState.Loading
) {
    val screenState: LoadingState
        get() = listOf(
            loadingHeroes
        ).getCombinedLoadingState()
}
