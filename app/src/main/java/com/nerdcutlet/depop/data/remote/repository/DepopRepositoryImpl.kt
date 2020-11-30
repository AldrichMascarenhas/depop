package com.nerdcutlet.depop.data.remote.repository


import com.nerdcutlet.depop.data.remote.retrofit.response.DepopItemResponse
import com.nerdcutlet.depop.data.remote.retrofit.response.DepopResponse
import com.nerdcutlet.depop.data.remote.retrofit.service.DepopService
import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.domain.model.ProductDomainModel
import com.nerdcutlet.depop.domain.repository.FlowStatus
import com.nerdcutlet.depop.domain.repository.DepopRepository
import retrofit2.Response
import kotlinx.coroutines.flow.Flow

class DepopRepositoryImpl(
    private val depopService: DepopService
) : DepopRepository {

    override suspend fun getProducts(): Flow<Status<List<ProductDomainModel>>> {

        return object : FlowStatus<DepopResponse, List<ProductDomainModel>>() {

            override suspend fun networkCall(): Response<DepopResponse> {
                return depopService.getCharacters()
            }

            override fun mapData(requestType: DepopResponse): List<ProductDomainModel> {
                return requestType.objects
                    .map {
                        ProductDomainModel(
                            it.id,
                            it.description,
                            it.picturesData.first().formats.p1.url
                        )
                    }

            }
        }.asFlow
    }

    override suspend fun getProductById(id: String): Flow<Status<ProductDetailDomainModel>> {

        return object : FlowStatus<DepopItemResponse, ProductDetailDomainModel>() {

            override suspend fun networkCall(): Response<DepopItemResponse> {
                return depopService.getCharacterById(id)
            }

            override fun mapData(requestType: DepopItemResponse): ProductDetailDomainModel {


                val images = requestType.picturesData.map {
                    it.formats.p0.url
                }
                return ProductDetailDomainModel(
                    requestType.id,
                    requestType.description,
                    images
                )
            }
        }.asFlow


    }
}
