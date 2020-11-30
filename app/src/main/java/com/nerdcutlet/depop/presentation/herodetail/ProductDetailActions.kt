package com.nerdcutlet.depop.presentation.herodetail

sealed class ProductDetailActions {
    object OnResume : ProductDetailActions()
    object LoadSquadProductState : ProductDetailActions()
}
