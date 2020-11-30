package com.nerdcutlet.depop.presentation.productdetail

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
    internal lateinit var productDetailFragmentArgs: ProductDetailFragmentArgs

    private lateinit var productDetailViewModel: ProductDetailViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        productDetailViewModel = ProductDetailViewModel(
            depopGateway = depopGateway,
            args = productDetailFragmentArgs
        )
    }

    @Test
    fun `verify state when OnResume is called`() {

        coroutineScope.runBlockingTest {

            //Given an expected flow
            val getProductDetailFlow = flow {
                emit(Status.Loading)
                delay(10)
                emit(
                    Status.Success(MockData.getProductDetailDomainModel())
                )
            }


            // Mock Gateway
            every { productDetailFragmentArgs.id } returns "1"
            coEvery { depopGateway.getProductById(any()) } returns getProductDetailFlow

            // When
            productDetailViewModel.sendAction(ProductDetailActions.OnResume)

            // Then
            productDetailViewModel.stateLiveData.value shouldBeEqualTo ProductDetailState(
                loadingProductState = LoadingState.Loading
            )

            coroutineScope.advanceTimeBy(10)

            productDetailViewModel.stateLiveData.value shouldBeEqualTo ProductDetailState(
                product = MockData.getProductDetailDomainModel(),
                loadingProductState = LoadingState.Ready
            )

        }
    }
}