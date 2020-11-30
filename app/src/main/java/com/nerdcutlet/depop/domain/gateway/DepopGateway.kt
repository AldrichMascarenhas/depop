package com.nerdcutlet.depop.domain.gateway

import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow

interface DepopGateway {

    suspend fun getProducts(): Flow<Status<List<ProductDomainModel>>>

    suspend fun getProductById(id: String): Flow<Status<ProductDetailDomainModel>>
}
