package com.nerdcutlet.marvel.data.remote.repository

import com.nerdcutlet.marvel.data.MockData
import com.nerdcutlet.marvel.data.local.dao.MarvelDao
import com.nerdcutlet.marvel.data.remote.retrofit.service.MarvelService
import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.repository.MarvelRepository
import com.nerdcutlet.marvel.utils.MainCoroutineScopeRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(JUnit4::class)
internal class MarvelRepositoryImplTest {

    @MockK
    internal lateinit var marvelService: MarvelService

    @MockK
    internal lateinit var marvelDao: MarvelDao

    lateinit var marvelRepository: MarvelRepository

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        marvelRepository = MarvelRepositoryImpl(
            marvelService, marvelDao
        )
    }

    @Test
    fun `verify get marvel characters`() {

        coroutinesTestRule.runBlockingTest {

            // Given
            coEvery {
                marvelService.getCharacters(
                    offset = 0,
                    orderBy = "name"
                )
            } returns Response.success(MockData.getCharacterResponse())

            // When
            val flow = marvelRepository.getMarvelCharacters(offset = 0)

            // Then
            flow.collect {
                if (it is Status.Success) it shouldBeEqualTo Status.Success(
                   MockData.getListHeroDomainModel()
                )
                if (it is Status.Loading) it shouldBeEqualTo Status.Loading
            }

        }
    }

    @Test
    fun `verify get marvel character by ID`() {

        coroutinesTestRule.runBlockingTest {

            // Given
            coEvery {
                marvelService.getCharacterById(
                    characterId = 0
                )
            } returns Response.success(MockData.getCharacterResponse())

            // When
            val flow = marvelRepository.getMarvelCharacterById(id = 0)

            // Then
            flow.collect {

                if (it is Status.Success) it shouldBeEqualTo Status.Success(
                    MockData.getHeroDomainModel()
                )
                if (it is Status.Loading) it shouldBeEqualTo Status.Loading
            }

        }
    }

}