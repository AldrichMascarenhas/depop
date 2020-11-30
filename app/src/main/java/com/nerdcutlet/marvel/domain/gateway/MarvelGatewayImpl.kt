package com.nerdcutlet.marvel.domain.gateway

import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDetailDomainModel
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.domain.usecase.*
import kotlinx.coroutines.flow.Flow

class MarvelGatewayImpl(
    private val getHeroesUseCase: GetHeroesUseCase,
    private val getHeroByIdUseCase: GetHeroByIdUseCase
) : MarvelGateway {

    override suspend fun getHeroes(): Flow<Status<List<HeroDomainModel>>> {
        return getHeroesUseCase.execute()
    }

    override suspend fun getHeroById(id: String): Flow<Status<HeroDetailDomainModel>> {
        return getHeroByIdUseCase.execute(id)
    }

}
