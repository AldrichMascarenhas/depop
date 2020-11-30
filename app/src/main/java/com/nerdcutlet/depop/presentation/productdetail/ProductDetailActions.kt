package com.nerdcutlet.depop.presentation.productdetail

sealed class ProductDetailActions {
    object OnResume : ProductDetailActions()
    object LoadSquadProductState : ProductDetailActions()
}
