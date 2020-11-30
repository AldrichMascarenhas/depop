package com.nerdcutlet.depop.domain.model

data class ProductDomainModel(
    val id: Int,
    val description: String,
    val image: String
)

data class ProductDetailDomainModel(
    val id: Int,
    val description: String,
    val image: List<String>
)
