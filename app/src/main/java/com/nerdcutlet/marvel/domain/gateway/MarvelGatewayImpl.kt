package com.nerdcutlet.marvel.domain.gateway

import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.domain.usecase.*
import kotlinx.coroutines.flow.Flow

class MarvelGatewayImpl(
    private val addHeroToSquadUseCase: AddHeroToSquadUseCase,
    private val getHeroesUseCase: GetHeroesUseCase,
    private val getHeroByIdUseCase: GetHeroByIdUseCase,
    private val getSquadHeroesUseCase: GetSquadHeroesUseCase,
    private val removeHeroFromSquadUseCase: RemoveHeroFromSquadUseCase
) : MarvelGateway {

    override suspend fun addHeroToSquad(heroDomainModel: HeroDomainModel) {
        return addHeroToSquadUseCase.execute(heroDomainModel = heroDomainModel)
    }

    override suspend fun getHeroes(offset: Int): Flow<Status<List<HeroDomainModel>>> {
        return getHeroesUseCase.execute(offset)
    }

    override suspend fun getHeroById(id: Int): Flow<Status<HeroDomainModel>> {
        return getHeroByIdUseCase.execute(id)
    }

    override suspend fun getSquadHeroes(): Flow<Status<List<HeroDomainModel>>> {
        return getSquadHeroesUseCase.execute()
    }

    override suspend fun removeHeroFromSquad(id: Int) {
        return removeHeroFromSquadUseCase.execute(id)
    }
}
