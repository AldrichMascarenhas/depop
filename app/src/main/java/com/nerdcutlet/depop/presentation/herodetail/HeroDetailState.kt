package com.nerdcutlet.depop.presentation.herodetail

import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.presentation.utils.LoadingState
import com.nerdcutlet.depop.presentation.utils.getCombinedLoadingState

data class HeroDetailState(
    val loadingHeroState: LoadingState = LoadingState.Loading,
    val product: ProductDetailDomainModel? = null
) {

    val screenState: LoadingState
        get() = listOf(
            loadingHeroState
        ).getCombinedLoadingState()
}
