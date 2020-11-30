package com.nerdcutlet.depop.presentation.herolist

import androidx.lifecycle.viewModelScope
import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.gateway.DepopGateway
import com.nerdcutlet.depop.domain.model.ProductDomainModel
import com.nerdcutlet.depop.presentation.base.BaseViewModel
import com.nerdcutlet.depop.presentation.utils.LoadingState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val depopGateway: DepopGateway
) : BaseViewModel<ProductListState, ProductListActions>(
    ProductListState()
) {

    override fun reducer(action: ProductListActions) {
        return when (action) {
            ProductListActions.LoadProducts -> getHeroes()
        }
    }


    private fun getHeroes() {
        viewModelScope.launch {
            depopGateway.getProducts().collect {
                state = reduceHeroes(it)
            }
        }
    }


    private fun reduceHeroes(status: Status<List<ProductDomainModel>>): ProductListState {
        return when (status) {
            is Status.Success -> {
                state.copy(
                    loadingHeroes = LoadingState.Ready,
                    products = status.data
                )
            }
            is Status.Error -> state.copy(
                loadingHeroes = LoadingState.Error
            )
            is Status.Loading -> state.copy(
                loadingHeroes =LoadingState.Loading
            )
        }
    }

}
