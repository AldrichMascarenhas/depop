package com.nerdcutlet.marvel.domain.usecase

import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDetailDomainModel
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

class GetHeroByIdUseCase(
    private val marvelRepository: MarvelRepository
) {

    suspend fun execute(
        id: String
    ): Flow<Status<HeroDetailDomainModel>> {
       return marvelRepository.getMarvelCharacterById(id = id)
    }
}
