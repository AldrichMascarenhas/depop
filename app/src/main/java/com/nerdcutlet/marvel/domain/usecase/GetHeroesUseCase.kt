package com.nerdcutlet.marvel.domain.usecase

import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

class GetHeroesUseCase(
    private val marvelRepository: MarvelRepository
) {

    suspend fun execute(
        offset: Int
    ): Flow<Status<List<HeroDomainModel>>> {
       return marvelRepository.getMarvelCharacters(offset = offset)
    }
}