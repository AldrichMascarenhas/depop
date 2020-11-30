package com.nerdcutlet.depop.data

import com.nerdcutlet.depop.data.remote.retrofit.response.*
import com.nerdcutlet.depop.domain.model.ProductDetailDomainModel
import com.nerdcutlet.depop.domain.model.ProductDomainModel

object MockData {

    fun getProductResponse(): DepopResponse {
        return DepopResponse(
            products = listOf(
                Product(
                    id = 1,
                    picturesData = listOf(
                        PicturesData(
                            formats = Formats(
                                p0 = P0("img-0")
                            ),
                            id = 1
                        )
                    ),
                    description = ""
                )
            )
        )
    }

    fun getProductDetailResponse(): DepopItemResponse {
        return DepopItemResponse(
            id = 1,
            picturesData = listOf(
                PicturesData(
                    formats = Formats(
                        p0 = P0("img-0")
                    ),
                    id = 1
                )
            ),
            description = ""
        )
    }

    fun getProductDomainModel() = ProductDomainModel(
        id = 1,
        image = "img",
        description = ""
    )

    fun getProductDetailDomainModel() = ProductDetailDomainModel(
        id = 1,
        image = listOf("img-0"),
        description = ""
    )

    fun getListProductDomainModel() = listOf(getProductDomainModel())

}