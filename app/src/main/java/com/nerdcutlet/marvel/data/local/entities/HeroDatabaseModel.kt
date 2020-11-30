package com.nerdcutlet.marvel.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nerdcutlet.marvel.domain.model.HeroDomainModel

@Entity(tableName = SQUAD_HERO_TABLE)
data class HeroDatabaseModel(
    @PrimaryKey val id: Int,
    val name: String,
    val heroThumbnailPath: String,
    val heroThumbnailExtension: String
)

fun HeroDatabaseModel.toDomainModel(): HeroDomainModel {
    return HeroDomainModel(
        id = id,
        name = name,
        description = "",
        heroThumbnailPath = heroThumbnailPath,
        heroThumbnailExtension = heroThumbnailExtension
    )
}

const val SQUAD_HERO_TABLE = "squad_hero_table"
