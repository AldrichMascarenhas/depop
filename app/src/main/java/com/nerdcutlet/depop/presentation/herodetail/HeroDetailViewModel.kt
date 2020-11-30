package com.nerdcutlet.depop.presentation.herodetail

import androidx.lifecycle.viewModelScope
import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.gateway.DepopGateway
import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.presentation.base.BaseViewModel
import com.nerdcutlet.depop.presentation.utils.LoadingState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HeroDetailViewModel(
    private val depopGateway: DepopGateway,
    private val args: HeroDetailFragmentArgs
) : BaseViewModel<HeroDetailState, HeroDetailActions>(
    HeroDetailState()
) {

    private fun loadData() {
        getHero()
       // getHeroState()
    }

    override fun reducer(action: HeroDetailActions) {
       return when (action) {
            is HeroDetailActions.LoadSquadHeroState -> {

            }
            is HeroDetailActions.OnResume -> { loadData() }
        }
    }


    private fun getHero() {
        viewModelScope.launch {
            depopGateway.getProductById(args.id).collect {
                state = reduce(it)
            }
        }
    }

//    private fun getHeroState() {
//        viewModelScope.launch {
//            marvelGateway.getSquadHeroes().collect {
//                state = reduceLike(it)
//            }
//        }
//    }


    private fun reduce(status: Status<ProductDetailDomainModel>): HeroDetailState {
        return when (status) {
            is Status.Success -> {
                state.copy(
                    loadingHeroState = LoadingState.Ready,
                    product = status.data
                )
            }
            is Status.Error -> state.copy(
                loadingHeroState = LoadingState.Error
            )
            is Status.Loading -> state.copy(
                loadingHeroState = LoadingState.Loading
            )
        }
    }
}
