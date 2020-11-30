package com.nerdcutlet.marvel.presentation.herodetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nerdcutlet.marvel.data.MockData
import com.nerdcutlet.marvel.domain.Status
import com.nerdcutlet.marvel.domain.gateway.MarvelGateway
import com.nerdcutlet.marvel.presentation.utils.LoadingState
import com.nerdcutlet.marvel.utils.MainCoroutineScopeRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
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
class HeroDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    internal lateinit var marvelGateway: MarvelGateway

    @MockK
    internal lateinit var heroDetailFragmentArgs: HeroDetailFragmentArgs

    private lateinit var heroDetailViewModel: HeroDetailViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        heroDetailViewModel = HeroDetailViewModel(
            marvelGateway = marvelGateway,
            args = heroDetailFragmentArgs
        )
    }

    @Test
    fun `verify state when OnResume is called`() {

        coroutineScope.runBlockingTest {

            //Given an expected flow
            val getHeroFlow = flow {
                emit(Status.Loading)
                delay(10)
                emit(
                    Status.Success(MockData.getHeroDomainModel())
                )
            }

            val getSquadFlow = flow {
                emit(Status.Loading)
                delay(5)
                emit(
                    Status.Success(MockData.getListHeroDomainModel())
                )
            }

            // Mock Gateway
            every { heroDetailFragmentArgs.heroID } returns 1
            coEvery { marvelGateway.getSquadHeroes() } returns getSquadFlow
            coEvery { marvelGateway.getHeroById(any()) } returns getHeroFlow

            // When
            heroDetailViewModel.sendAction(HeroDetailActions.OnResume)

            // Then
            heroDetailViewModel.stateLiveData.value shouldBeEqualTo HeroDetailState(
                loadingHeroState = LoadingState.Loading,
                isHeroInSquadLoadingState = LoadingState.Loading
            )

            coroutineScope.advanceTimeBy(5)

            // Then
            heroDetailViewModel.stateLiveData.value shouldBeEqualTo HeroDetailState(
                isLiked = true,
                loadingHeroState = LoadingState.Loading,
                isHeroInSquadLoadingState = LoadingState.Ready
            )

            coroutineScope.advanceTimeBy(10)

            heroDetailViewModel.stateLiveData.value shouldBeEqualTo HeroDetailState(
                isLiked = true,
                hero = MockData.getHeroDomainModel(),
                loadingHeroState = LoadingState.Ready,
                isHeroInSquadLoadingState = LoadingState.Ready
            )

        }
    }
}