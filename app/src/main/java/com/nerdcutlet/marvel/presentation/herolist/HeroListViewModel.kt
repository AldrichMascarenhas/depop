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
            HeroListActions.LoadHeroes -> getHeroes(state.offset)
            HeroListActions.LoadSquad -> getSquad()
        }
    }

    private fun getSquad() {
        viewModelScope.launch {
            marvelGateway.getSquadHeroes().collect {
                state = reduceSquadHeroes(it)
            }
        }
    }

    private fun getHeroes(offset: Int) {
        viewModelScope.launch {
            marvelGateway.getHeroes(offset).collect {
                state = reduceHeroes(it)
            }
        }
    }

    private fun reduceSquadHeroes(status: Status<List<HeroDomainModel>>): HeroListState {
        return when (status) {
            is Status.Success -> state.copy(
                squadHeroesLoadingState = LoadingState.Ready,
                squadHeroes = status.data
            )
            is Status.Error -> state.copy(
                squadHeroesLoadingState = LoadingState.Error
            )
            Status.Loading -> state.copy(
                squadHeroesLoadingState = LoadingState.Loading
            )
        }
    }

    private fun reduceHeroes(status: Status<List<HeroDomainModel>>): HeroListState {
        return when (status) {
            is Status.Success -> {
                val newOffset = state.offset

                state.copy(
                    loadingHeroes = messagesOrDefault(LoadingState.Ready, status.data),
                    heroes = setItems(status.data),
                    offset = newOffset + OFFSET_VALUE

                )
            }
            is Status.Error -> state.copy(
                loadingHeroes = messagesOrDefault(LoadingState.Error)
            )
            is Status.Loading -> state.copy(
                loadingHeroes = messagesOrDefault(LoadingState.Loading)
            )
        }
    }

    private fun setItems(
        data: List<HeroDomainModel>
    ): List<HeroDomainModel> {
        val messages = state.heroes
        val new = messages.toMutableList()
        new.addAll(
            data
        )
        return new.toList()
    }

    private fun messagesOrDefault(
        defaultState: LoadingState,
        data: List<HeroDomainModel>? = null
    ): LoadingState {
        val messages = state.heroes

        return if (messages.isNotEmpty()) {
            val new = messages.toMutableList()
            if (data !== null) {
                new.addAll(
                    data
                )
            }
            LoadingState.Ready
        } else {
            defaultState
        }
    }

    private fun reduceOffset(): HeroListState {
        val newOffset = state.offset + OFFSET_VALUE

        return state.copy(
            offset = newOffset
        )
    }

    companion object {
        const val OFFSET_VALUE: Int = 20
    }
}
