package com.nerdcutlet.marvel.domain.repository

import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun getMarvelCharacters(offset: Int): Flow<Status<List<HeroDomainModel>>>

    suspend fun getMarvelCharacterById(id: Int): Flow<Status<HeroDomainModel>>

    suspend fun addToMarvelSquad(heroDomainModel: HeroDomainModel)

    suspend fun removeFromMarvelSquad(heroId: Int)

    suspend fun getMarvelSquad(): Flow<Status<List<HeroDomainModel>>>
}
