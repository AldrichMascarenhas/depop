package com.nerdcutlet.marvel.presentation.herodetail

import androidx.lifecycle.viewModelScope
import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.gateway.MarvelGateway
import com.nerdcutlet.marvel.domain.model.HeroDetailDomainModel
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.presentation.base.BaseViewModel
import com.nerdcutlet.marvel.presentation.utils.LoadingState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HeroDetailViewModel(
    private val marvelGateway: MarvelGateway,
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
            marvelGateway.getHeroById(args.id).collect {
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


    private fun reduce(status: Status<HeroDetailDomainModel>): HeroDetailState {
        return when (status) {
            is Status.Success -> {
                state.copy(
                    loadingHeroState = LoadingState.Ready,
                    hero = status.data
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
