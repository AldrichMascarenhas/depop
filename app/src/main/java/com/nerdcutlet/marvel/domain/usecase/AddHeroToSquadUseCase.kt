package com.nerdcutlet.marvel.domain.usecase

import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.domain.repository.MarvelRepository

class AddHeroToSquadUseCase(
    private val marvelRepository: MarvelRepository
) {

    suspend fun execute(
        heroDomainModel: HeroDomainModel
    ) {
        return marvelRepository.addToMarvelSquad(heroDomainModel = heroDomainModel)
    }
}
