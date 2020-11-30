package com.nerdcutlet.depop.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nerdcutlet.depop.data.MockDataAndroidTest
import com.nerdcutlet.depop.data.local.dao.MarvelDao
import com.nerdcutlet.depop.domain.model.ProductDomainModel
import com.nerdcutlet.depop.domain.model.toHeroDatabaseModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
 class MarvelHeroDatabaseTest{

    private lateinit var marvelDao: MarvelDao
    private lateinit var db: MarvelHeroDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MarvelHeroDatabase::class.java).build()
        marvelDao = db.marvelDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun addHeroToMarvelSquad() = runBlocking {

        // Given
        val product: ProductDomainModel = MockDataAndroidTest.getHeroDomainModel()

        // When
        marvelDao.addToMarvelSquad(product.toHeroDatabaseModel())

        // Then
        val savedHero = marvelDao.getLikedHeroes()
        Assert.assertEquals(savedHero.first(), product.toHeroDatabaseModel())
    }

    @Test
    @Throws(Exception::class)
    fun addHeroToMarvelSquadAndDelete() = runBlocking {

        // Given
        val product: ProductDomainModel = MockDataAndroidTest.getHeroDomainModel()

        // When
        marvelDao.addToMarvelSquad(product.toHeroDatabaseModel())

        // Then
        val savedHero = marvelDao.getLikedHeroes()
        Assert.assertEquals(savedHero.first(), product.toHeroDatabaseModel())

        // When
        marvelDao.removeFromMarvelSquad(id = 1)

        // Then
        val savedHeroesAfterDelete = marvelDao.getLikedHeroes()
        Assert.assertTrue(savedHeroesAfterDelete.isEmpty())
    }
}