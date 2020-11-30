package com.nerdcutlet.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nerdcutlet.marvel.data.local.entities.SQUAD_HERO_TABLE
import com.nerdcutlet.marvel.data.local.entities.HeroDatabaseModel

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToMarvelSquad(hero: HeroDatabaseModel)

    @Query("DELETE from $SQUAD_HERO_TABLE WHERE id = :id")
    suspend fun removeFromMarvelSquad(id: Int)

    @Query("SELECT * from $SQUAD_HERO_TABLE")
    suspend fun getLikedHeroes(): List<HeroDatabaseModel>
}
