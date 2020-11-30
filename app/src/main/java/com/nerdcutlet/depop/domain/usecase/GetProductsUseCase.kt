package com.nerdcutlet.depop.domain.usecase

import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.model.ProductDomainModel
import com.nerdcutlet.depop.domain.repository.DepopRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val depopRepository: DepopRepository
) {

    suspend fun execute(): Flow<Status<List<ProductDomainModel>>> {
       return depopRepository.getProducts()
    }
}
