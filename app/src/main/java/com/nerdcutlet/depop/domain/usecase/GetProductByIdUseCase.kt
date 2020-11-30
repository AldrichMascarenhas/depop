package com.nerdcutlet.depop.domain.usecase

import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.domain.repository.DepopRepository
import kotlinx.coroutines.flow.Flow

class GetProductByIdUseCase(
    private val depopRepository: DepopRepository
) {

    suspend fun execute(
        id: String
    ): Flow<Status<ProductDetailDomainModel>> {
       return depopRepository.getProductById(id = id)
    }
}
