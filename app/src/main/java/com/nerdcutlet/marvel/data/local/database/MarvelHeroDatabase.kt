package com.nerdcutlet.marvel.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nerdcutlet.marvel.data.local.dao.MarvelDao
import com.nerdcutlet.marvel.data.local.entities.HeroDatabaseModel

@Database(
    entities = [
        HeroDatabaseModel::class
    ], version = 1
)
abstract class MarvelHeroDatabase : RoomDatabase() {

    abstract fun marvelDao(): MarvelDao
}

const val MARVEL_HERO_DATABASE = "marvel-hero-databse"
