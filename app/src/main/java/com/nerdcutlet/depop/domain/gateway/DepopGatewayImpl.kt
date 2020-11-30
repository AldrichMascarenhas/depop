package com.nerdcutlet.depop.domain.gateway

import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.domain.model.ProductDomainModel
import com.nerdcutlet.depop.domain.usecase.*
import kotlinx.coroutines.flow.Flow

class DepopGatewayImpl(
    private val getHeroesUseCase: GetProductsUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase
) : DepopGateway {

    override suspend fun getProducts(): Flow<Status<List<ProductDomainModel>>> {
        return getHeroesUseCase.execute()
    }

    override suspend fun getProductById(id: String): Flow<Status<ProductDetailDomainModel>> {
        return getProductByIdUseCase.execute(id)
    }

}
