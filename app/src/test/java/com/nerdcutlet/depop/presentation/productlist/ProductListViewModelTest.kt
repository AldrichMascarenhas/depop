package com.nerdcutlet.depop.presentation.productlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nerdcutlet.depop.data.MockData
import com.nerdcutlet.depop.domain.Status
import com.nerdcutlet.depop.domain.gateway.DepopGateway
import com.nerdcutlet.depop.presentation.utils.LoadingState
import com.nerdcutlet.depop.utils.MainCoroutineScopeRule
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
class ProductListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    internal lateinit var depopGateway: DepopGateway


    private lateinit var productListViewModel: ProductListViewModel


    @Before
    fun setup() {
        MockKAnnotations.init(this)

        productListViewModel = ProductListViewModel(
            depopGateway = depopGateway
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
                    Status.Success(MockData.getListProductDomainModel())
                )
            }

            // Mock Gateway
            coEvery { depopGateway.getProducts() } returns flow

            // When
            productListViewModel.sendAction(ProductListActions.LoadProducts)

            // Then
            productListViewModel.stateLiveData.value shouldBeEqualTo ProductListState(
                loadingProducts = LoadingState.Loading
            )

            coroutineScope.advanceTimeBy(10)

            // Then
            productListViewModel.stateLiveData.value shouldBeEqualTo ProductListState(
                loadingProducts = LoadingState.Ready,
                products = MockData.getListProductDomainModel()
            )
        }
    }

}