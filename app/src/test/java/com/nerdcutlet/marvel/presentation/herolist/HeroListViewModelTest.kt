package com.nerdcutlet.marvel.presentation.herolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nerdcutlet.marvel.data.MockData
import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.gateway.MarvelGateway
import com.nerdcutlet.marvel.presentation.utils.LoadingState
import com.nerdcutlet.marvel.utils.MainCoroutineScopeRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HeroListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    internal lateinit var marvelGateway: MarvelGateway


    private lateinit var heroListViewModel: HeroListViewModel


    @Before
    fun setup() {
        MockKAnnotations.init(this)

        heroListViewModel = HeroListViewModel(
            marvelGateway = marvelGateway
        )

    }

    @Test
    fun `verify state when Load Hero is called`() {

        coroutineScope.runBlockingTest {

            //Given an expected flow
            val flow = flow {
                emit(Status.Loading)
                delay(10)
                emit(
                    Status.Success(MockData.getListHeroDomainModel())
                )
            }

            // Mock Gateway
            coEvery { marvelGateway.getHeroes(any()) } returns flow

            // When
            heroListViewModel.sendAction(HeroListActions.LoadHeroes)

            // Then
            heroListViewModel.stateLiveData.value shouldBeEqualTo HeroListState(
                offset = 0,
                loadingHeroes = LoadingState.Loading
            )

            coroutineScope.advanceTimeBy(10)

            // Then
            heroListViewModel.stateLiveData.value shouldBeEqualTo HeroListState(
                offset = 20,
                loadingHeroes = LoadingState.Ready,
                heroes = MockData.getListHeroDomainModel()
            )
        }
    }

    @Test
    fun `verify state when Load Squad is called`() {

        coroutineScope.runBlockingTest {

            //Given an expected flow
            val flow = flow {
                emit(Status.Loading)
                delay(10)
                emit(
                    Status.Success(MockData.getListHeroDomainModel())
                )
            }

            // Mock Gateway
            coEvery { marvelGateway.getSquadHeroes() } returns flow

            // When
            heroListViewModel.sendAction(HeroListActions.LoadSquad)

            // Then
            heroListViewModel.stateLiveData.value shouldBeEqualTo HeroListState(
                offset = 0,
                squadHeroesLoadingState = LoadingState.Loading
            )

            coroutineScope.advanceTimeBy(10)

            // Then
            heroListViewModel.stateLiveData.value shouldBeEqualTo HeroListState(
                squadHeroesLoadingState = LoadingState.Ready,
                squadHeroes = MockData.getListHeroDomainModel()
            )
        }
    }

}