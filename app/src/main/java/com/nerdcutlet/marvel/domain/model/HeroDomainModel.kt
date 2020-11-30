package com.nerdcutlet.marvel.domain.model

import com.nerdcutlet.marvel.data.local.entities.HeroDatabaseModel

data class HeroDomainModel(
    val id: Int,
    val name: String,
    val heroThumbnailPath: String,
    val heroThumbnailExtension: String,
    val description: String
)

fun HeroDomainModel.toHeroDatabaseModel(): HeroDatabaseModel {
    return HeroDatabaseModel(
        id = id,
        name = name,
        heroThumbnailPath = heroThumbnailPath,
        heroThumbnailExtension = heroThumbnailExtension
    )
}
