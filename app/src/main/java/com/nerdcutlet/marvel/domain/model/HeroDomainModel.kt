package com.nerdcutlet.marvel.domain.model


data class HeroDomainModel(
    val id: Int,
    val description : String,
    val image : String
)

data class HeroDetailDomainModel(
    val id: Int,
    val description : String,
    val image : List<String>
)
