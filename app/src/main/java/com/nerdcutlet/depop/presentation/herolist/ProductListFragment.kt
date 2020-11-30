package com.nerdcutlet.depop.presentation.herolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nerdcutlet.depop.R
import com.nerdcutlet.depop.presentation.items.ConnectionErrorItem
import com.nerdcutlet.depop.presentation.items.ProductLoadingItem
import com.nerdcutlet.depop.presentation.utils.InfiniteScrollListener
import com.nerdcutlet.depop.presentation.utils.LoadingState
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_product_list.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class ProductListFragment : Fragment(), DIAware {

    override val di: DI by di()
    private val viewModel: ProductListViewModel by instance()
    private val productListContentRenderer: ProductListContentRenderer by instance()

    private val contentSection = Section()

    private val stateObserver = Observer<ProductListState> { state ->
        when (state.screenState) {
            LoadingState.Ready -> renderReady(state)
            LoadingState.Loading -> renderLoading()
            LoadingState.Error -> renderError()
        }
    }

    private fun renderError() {
        contentSection.update(
            listOf(ConnectionErrorItem(
                customTitle = requireContext().resources.getString(R.string.network_error_message)
            ) {
                viewModel.sendAction(ProductListActions.LoadProducts)
            })
        )
    }

    private fun renderLoading() {
        contentSection.removeHeader()
        contentSection.update(listOf(ProductLoadingItem))
    }

    private fun renderReady(state: ProductListState) {

        if (state.products.isNotEmpty()) {
            contentSection.update(productListContentRenderer.renderItems(state.products) {
                val navDirections = ProductListFragmentDirections.actionHeroListToHeroDetail(it.toString())
                this.findNavController().navigate(navDirections)
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel.stateLiveData.observe(viewLifecycleOwner, stateObserver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.sendAction(ProductListActions.LoadProducts)
    }

    private fun initRecyclerView() {

        fragment_hero_recyclerview.apply {
            val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
                add(contentSection)
            }
            val layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            setLayoutManager(layoutManager)

            adapter = groupAdapter

            addOnScrollListener(object : InfiniteScrollListener(layoutManager) {
                override fun onLoadMore() {
                    viewModel.sendAction(ProductListActions.LoadProducts)
                }
            })
        }
    }
}
