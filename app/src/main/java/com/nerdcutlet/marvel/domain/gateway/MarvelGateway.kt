package com.nerdcutlet.marvel.domain.gateway

import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import kotlinx.coroutines.flow.Flow

interface MarvelGateway {

    suspend fun addHeroToSquad(heroDomainModel: HeroDomainModel)

    suspend fun getHeroes(offset: Int): Flow<Status<List<HeroDomainModel>>>

    suspend fun getHeroById(id: Int): Flow<Status<HeroDomainModel>>

    suspend fun getSquadHeroes(): Flow<Status<List<HeroDomainModel>>>

    suspend fun removeHeroFromSquad(id: Int)
}
