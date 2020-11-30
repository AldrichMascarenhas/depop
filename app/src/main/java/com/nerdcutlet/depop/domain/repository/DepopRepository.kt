package com.nerdcutlet.depop.domain.repository

import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow

interface DepopRepository {

    suspend fun getProducts(): Flow<Status<List<ProductDomainModel>>>

    suspend fun getProductById(id: String): Flow<Status<ProductDetailDomainModel>>

}
