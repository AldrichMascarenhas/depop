package com.nerdcutlet.depop.presentation.productdetail

import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.presentation.utils.LoadingState
import com.nerdcutlet.depop.presentation.utils.getCombinedLoadingState

data class ProductDetailState(
    val loadingProductState: LoadingState = LoadingState.Loading,
    val product: ProductDetailDomainModel? = null
) {

    val screenState: LoadingState
        get() = listOf(
            loadingProductState
        ).getCombinedLoadingState()
}
