package com.nerdcutlet.marvel.domain.repository

import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDetailDomainModel
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun getMarvelCharacters(): Flow<Status<List<HeroDomainModel>>>

    suspend fun getMarvelCharacterById(id: String): Flow<Status<HeroDetailDomainModel>>

}
