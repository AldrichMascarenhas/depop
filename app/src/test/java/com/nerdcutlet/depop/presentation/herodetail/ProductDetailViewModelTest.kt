package com.nerdcutlet.depop.presentation.herodetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nerdcutlet.depop.data.MockData
import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.gateway.DepopGateway
import com.nerdcutlet.depop.presentation.utils.LoadingState
import com.nerdcutlet.depop.utils.MainCoroutineScopeRule
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
class ProductDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    internal lateinit var depopGateway: DepopGateway

    @MockK
    internal lateinit var heroDetailFragmentArgs: HeroDetailFragmentArgs

    private lateinit var productDetailViewModel: ProductDetailViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        productDetailViewModel = ProductDetailViewModel(
            marvelGateway = depopGateway,
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
            coEvery { depopGateway.getSquadHeroes() } returns getSquadFlow
            coEvery { depopGateway.getProductById(any()) } returns getHeroFlow

            // When
            productDetailViewModel.sendAction(ProductDetailActions.OnResume)

            // Then
            productDetailViewModel.stateLiveData.value shouldBeEqualTo ProductDetailState(
                loadingProductState = LoadingState.Loading,
                isHeroInSquadLoadingState = LoadingState.Loading
            )

            coroutineScope.advanceTimeBy(5)

            // Then
            productDetailViewModel.stateLiveData.value shouldBeEqualTo ProductDetailState(
                isLiked = true,
                loadingProductState = LoadingState.Loading,
                isHeroInSquadLoadingState = LoadingState.Ready
            )

            coroutineScope.advanceTimeBy(10)

            productDetailViewModel.stateLiveData.value shouldBeEqualTo ProductDetailState(
                isLiked = true,
                product = MockData.getHeroDomainModel(),
                loadingProductState = LoadingState.Ready,
                isHeroInSquadLoadingState = LoadingState.Ready
            )

        }
    }
}