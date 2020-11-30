package com.nerdcutlet.depop.data.remote.repository

import com.nerdcutlet.depop.data.MockData
import com.nerdcutlet.depop.data.local.dao.MarvelDao
import com.nerdcutlet.depop.data.remote.retrofit.service.DepopService
import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.repository.DepopRepository
import com.nerdcutlet.depop.utils.MainCoroutineScopeRule
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
internal class DepopRepositoryImplTest {

    @MockK
    internal lateinit var depopService: DepopService

    @MockK
    internal lateinit var marvelDao: MarvelDao

    lateinit var depopRepository: DepopRepository

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        depopRepository = DepopRepositoryImpl(
            depopService, marvelDao
        )
    }

    @Test
    fun `verify get marvel characters`() {

        coroutinesTestRule.runBlockingTest {

            // Given
            coEvery {
                depopService.getCharacters(
                    offset = 0,
                    orderBy = "name"
                )
            } returns Response.success(MockData.getCharacterResponse())

            // When
            val flow = depopRepository.getProducts(offset = 0)

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
                depopService.getCharacterById(
                    characterId = 0
                )
            } returns Response.success(MockData.getCharacterResponse())

            // When
            val flow = depopRepository.getProductById(id = 0)

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