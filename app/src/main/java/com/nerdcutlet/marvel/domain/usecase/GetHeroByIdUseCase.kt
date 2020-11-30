package com.nerdcutlet.marvel.domain.usecase

import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

class GetHeroByIdUseCase(
    private val marvelRepository: MarvelRepository
) {

    suspend fun execute(
        id: Int
    ): Flow<Status<HeroDomainModel>> {
        return marvelRepository.getMarvelCharacterById(id = id)
    }
}
