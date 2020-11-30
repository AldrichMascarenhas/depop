package com.nerdcutlet.marvel.data.remote.repository

import com.nerdcutlet.marvel.data.local.dao.MarvelDao
import com.nerdcutlet.marvel.data.local.entities.HeroDatabaseModel
import com.nerdcutlet.marvel.data.local.entities.toDomainModel
import com.nerdcutlet.marvel.data.remote.retrofit.response.CharactersResponse
import com.nerdcutlet.marvel.data.remote.retrofit.response.toHeroDomainModel
import com.nerdcutlet.marvel.data.remote.retrofit.service.MarvelService
import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.domain.model.toHeroDatabaseModel
import com.nerdcutlet.marvel.domain.repository.FlowStatus
import com.nerdcutlet.marvel.domain.repository.MarvelRepository
import retrofit2.Response
import kotlinx.coroutines.flow.Flow

class MarvelRepositoryImpl(
    private val marvelService: MarvelService,
    private val marvelDao: MarvelDao
) : MarvelRepository {

    override suspend fun getMarvelCharacters(offset: Int): Flow<Status<List<HeroDomainModel>>> {

        return object : FlowStatus<CharactersResponse, List<HeroDomainModel>>() {

            override suspend fun networkCall(): Response<CharactersResponse> {
                return marvelService.getCharacters(offset = offset, orderBy = "name")
            }

            override fun mapData(requestType: CharactersResponse): List<HeroDomainModel> {
                return requestType.data
                    .results
                    .map {
                        it.toHeroDomainModel()
                    }
            }

            override suspend fun databaseCall(): CharactersResponse? {
                return null
            }
        }.asFlow
    }

    override suspend fun getMarvelCharacterById(id: Int): Flow<Status<HeroDomainModel>> {

        return object : FlowStatus<CharactersResponse, HeroDomainModel>() {

            override suspend fun networkCall(): Response<CharactersResponse>? {
                return marvelService.getCharacterById(characterId = id)
            }

            override suspend fun databaseCall(): CharactersResponse? {
                return null
            }

            override fun mapData(requestType: CharactersResponse): HeroDomainModel {
                return requestType
                    .data
                    .results
                    .first()
                    .run {
                        this.toHeroDomainModel()
                    }
            }
        }.asFlow
    }

    override suspend fun getMarvelSquad(): Flow<Status<List<HeroDomainModel>>> {

        return object : FlowStatus<List<HeroDatabaseModel>, List<HeroDomainModel>>() {

            override suspend fun networkCall(): Response<List<HeroDatabaseModel>>? {
                return null
            }

            override fun mapData(requestType: List<HeroDatabaseModel>): List<HeroDomainModel> {
                return requestType.map {
                    it.toDomainModel()
                }
            }

            override suspend fun databaseCall(): List<HeroDatabaseModel> {
                return marvelDao.getLikedHeroes()
            }
        }.asFlow
    }

    override suspend fun addToMarvelSquad(heroDomainModel: HeroDomainModel) {
        val heroDatabaseModel = heroDomainModel.toHeroDatabaseModel()
        return marvelDao.addToMarvelSquad(heroDatabaseModel)
    }

    override suspend fun removeFromMarvelSquad(heroId: Int) {
        return marvelDao.removeFromMarvelSquad(heroId)
    }
}
