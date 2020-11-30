package com.nerdcutlet.marvel.presentation.herolist

import androidx.lifecycle.viewModelScope
import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.gateway.MarvelGateway
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.presentation.base.BaseViewModel
import com.nerdcutlet.marvel.presentation.utils.LoadingState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HeroListViewModel(
    private val marvelGateway: MarvelGateway
) : BaseViewModel<HeroListState, HeroListActions>(
    HeroListState()
) {

    override fun reducer(action: HeroListActions) {
        return when (action) {
            HeroListActions.LoadHeroes -> getHeroes()
        }
    }


    private fun getHeroes() {
        viewModelScope.launch {
            marvelGateway.getHeroes().collect {
                state = reduceHeroes(it)
            }
        }
    }


    private fun reduceHeroes(status: Status<List<HeroDomainModel>>): HeroListState {
        return when (status) {
            is Status.Success -> {
                state.copy(
                    loadingHeroes = LoadingState.Ready,
                    heroes = status.data
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
