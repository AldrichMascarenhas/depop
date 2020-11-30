package com.nerdcutlet.depop.presentation.productlist

import com.nerdcutlet.depop.domain.model.ProductDomainModel
import com.nerdcutlet.depop.presentation.utils.LoadingState
import com.nerdcutlet.depop.presentation.utils.getCombinedLoadingState

data class ProductListState(
    val products: List<ProductDomainModel> = emptyList(),
    val loadingProducts: LoadingState = LoadingState.Loading
) {
    val screenState: LoadingState
        get() = listOf(
            loadingProducts
        ).getCombinedLoadingState()
}
