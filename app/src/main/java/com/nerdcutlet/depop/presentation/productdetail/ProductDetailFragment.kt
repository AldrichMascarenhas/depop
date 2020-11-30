package com.nerdcutlet.depop.presentation.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nerdcutlet.depop.R
import com.nerdcutlet.depop.presentation.items.ConnectionErrorItem
import com.nerdcutlet.depop.presentation.items.ProductLoadingItem
import com.nerdcutlet.depop.presentation.utils.LoadingState
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_product_detail.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance
import org.kodein.di.newInstance

class ProductDetailFragment : Fragment(), DIAware {

    override val di: DI by di()

    private val args: ProductDetailFragmentArgs by navArgs()

    private val contentSection = Section()
    private val productDetailContentRenderer: ProductDetailContentRenderer by instance()

    private val viewModel: ProductDetailViewModel by newInstance {
        ProductDetailViewModel(instance(), args = args)
    }

    private val stateObserver = Observer<ProductDetailState> { state ->
        when (state.screenState) {
            LoadingState.Ready -> renderReady(state)
            LoadingState.Loading -> renderLoading()
            LoadingState.Error -> renderError()
        }
    }

    private fun renderError() {
        contentSection.update(listOf(ConnectionErrorItem(
            customTitle = requireContext().resources.getString(R.string.network_error_message)
        ) {
            viewModel.sendAction(ProductDetailActions.OnResume)
        }))
    }

    private fun renderReady(state: ProductDetailState) {
        contentSection.update(productDetailContentRenderer.renderItems(state, this.requireContext()) {
        })
    }

    private fun renderLoading() {
        contentSection.update(listOf(ProductLoadingItem))
    }

    override fun onResume() {
        super.onResume()
        viewModel.sendAction(ProductDetailActions.OnResume)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel.stateLiveData.observe(viewLifecycleOwner, stateObserver)
    }

    private fun initRecyclerView() {

        fragment_detail_recyclerview.apply {
            val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
                add(contentSection)
            }
            val layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            setLayoutManager(layoutManager)

            adapter = groupAdapter
        }
    }
}
