package com.nerdcutlet.marvel.data.remote.repository


import com.nerdcutlet.marvel.data.remote.retrofit.response.DepopItemResponse
import com.nerdcutlet.marvel.data.remote.retrofit.response.DepopResponse
import com.nerdcutlet.marvel.data.remote.retrofit.service.MarvelService
import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.model.HeroDetailDomainModel
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.domain.repository.FlowStatus
import com.nerdcutlet.marvel.domain.repository.MarvelRepository
import retrofit2.Response
import kotlinx.coroutines.flow.Flow

class MarvelRepositoryImpl(
    private val marvelService: MarvelService
) : MarvelRepository {

    override suspend fun getMarvelCharacters(): Flow<Status<List<HeroDomainModel>>> {

        return object : FlowStatus<DepopResponse, List<HeroDomainModel>>() {

            override suspend fun networkCall(): Response<DepopResponse> {
                return marvelService.getCharacters()
            }

            override fun mapData(requestType: DepopResponse): List<HeroDomainModel> {
                return requestType.objects
                    .map {
                        HeroDomainModel(
                            it.id,
                            it.description,
                            it.picturesData.first().formats.p1.url
                        )
                    }

            }
        }.asFlow
    }

    override suspend fun getMarvelCharacterById(id: String): Flow<Status<HeroDetailDomainModel>> {

        return object : FlowStatus<DepopItemResponse, HeroDetailDomainModel>() {

            override suspend fun networkCall(): Response<DepopItemResponse> {
                return marvelService.getCharacterById(id)
            }

            override fun mapData(requestType: DepopItemResponse): HeroDetailDomainModel {


                val images = requestType.picturesData.map {
                    it.formats.p0.url
                }
                return HeroDetailDomainModel(
                    requestType.id,
                    requestType.description,
                    images
                )
            }
        }.asFlow


    }
}
