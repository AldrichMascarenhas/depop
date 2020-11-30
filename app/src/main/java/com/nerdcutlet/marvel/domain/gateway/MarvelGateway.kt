package com.nerdcutlet.marvel.domain.gateway

import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDetailDomainModel
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import kotlinx.coroutines.flow.Flow

interface MarvelGateway {

    suspend fun getHeroes(): Flow<Status<List<HeroDomainModel>>>

    suspend fun getHeroById(id: String): Flow<Status<HeroDetailDomainModel>>
}
