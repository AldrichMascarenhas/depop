package com.nerdcutlet.marvel.presentation.herodetail

import androidx.lifecycle.viewModelScope
import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.gateway.MarvelGateway
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
        getHeroState()
    }

    override fun reducer(action: HeroDetailActions) {
       return when (action) {
            is HeroDetailActions.SquadAction -> handleAddAndRemove()
            is HeroDetailActions.LoadSquadHeroState -> getHeroState()
            is HeroDetailActions.OnResume -> { loadData() }
        }
    }

    private fun handleAddAndRemove() {
        viewModelScope.launch {
            if (state.isLiked) {
                marvelGateway.removeHeroFromSquad(id = args.heroID).apply {
                    sendAction(HeroDetailActions.LoadSquadHeroState)
                }
            } else {
                state.hero?.let { hero ->
                    marvelGateway.addHeroToSquad(hero).apply {
                        sendAction(HeroDetailActions.LoadSquadHeroState)
                    }
                }
            }
        }
    }

    private fun getHero() {
        viewModelScope.launch {
            marvelGateway.getHeroById(args.heroID).collect {
                state = reduce(it)
            }
        }
    }

    private fun getHeroState() {
        viewModelScope.launch {
            marvelGateway.getSquadHeroes().collect {
                state = reduceLike(it)
            }
        }
    }

    private fun reduceLike(status: Status<List<HeroDomainModel>>): HeroDetailState {
        return when (status) {
            is Status.Success -> {
                val doesLike = status.data.any {
                    it.id == args.heroID
                }
                state.copy(
                    isHeroInSquadLoadingState = LoadingState.Ready,
                    isLiked = doesLike
                )
            }
            is Status.Error -> state.copy(
                isHeroInSquadLoadingState = LoadingState.Error
            )
            is Status.Loading -> state.copy(
                isHeroInSquadLoadingState = LoadingState.Loading
            )
        }
    }

    private fun reduce(status: Status<HeroDomainModel>): HeroDetailState {
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
