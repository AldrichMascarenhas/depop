package com.nerdcutlet.marvel.domain.usecase

import com.nerdcutlet.marvel.domain.repository.MarvelRepository

class RemoveHeroFromSquadUseCase(
    private val marvelRepository: MarvelRepository
) {

    suspend fun execute(
        heroId: Int
    ) {
        return marvelRepository.removeFromMarvelSquad(heroId = heroId)
    }
}
