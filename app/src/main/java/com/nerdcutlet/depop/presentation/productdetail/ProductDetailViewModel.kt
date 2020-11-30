package com.nerdcutlet.depop.presentation.productdetail

import androidx.lifecycle.viewModelScope
import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.gateway.DepopGateway
import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.presentation.base.BaseViewModel
import com.nerdcutlet.depop.presentation.utils.LoadingState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val depopGateway: DepopGateway,
    private val args: ProductDetailFragmentArgs
) : BaseViewModel<ProductDetailState, ProductDetailActions>(
    ProductDetailState()
) {

    private fun loadData() {
        getProduct()
    }

    override fun reducer(action: ProductDetailActions) {
       return when (action) {
            is ProductDetailActions.LoadSquadProductState -> {
            }
            is ProductDetailActions.OnResume -> { loadData() }
        }
    }

    private fun getProduct() {
        viewModelScope.launch {
            depopGateway.getProductById(args.id).collect {
                state = reduce(it)
            }
        }
    }

    private fun reduce(status: Status<ProductDetailDomainModel>): ProductDetailState {
        return when (status) {
            is Status.Success -> {
                state.copy(
                    loadingProductState = LoadingState.Ready,
                    product = status.data
                )
            }
            is Status.Error -> state.copy(
                loadingProductState = LoadingState.Error
            )
            is Status.Loading -> state.copy(
                loadingProductState = LoadingState.Loading
            )
        }
    }
}
