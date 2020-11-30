package com.nerdcutlet.marvel.presentation.herodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nerdcutlet.marvel.R
import com.nerdcutlet.marvel.presentation.items.ConnectionErrorItem
import com.nerdcutlet.marvel.presentation.items.HeroLoadingItem
import com.nerdcutlet.marvel.presentation.utils.LoadingState
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_hero_detail.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance
import org.kodein.di.newInstance

class HeroDetailFragment : Fragment(), DIAware {

    override val di: DI by di()

    private val args: HeroDetailFragmentArgs by navArgs()

    private val contentSection = Section()
    private val heroDetailContentRenderer: HeroDetailContentRenderer by instance()

    private val viewModel: HeroDetailViewModel by newInstance {
        HeroDetailViewModel(instance(), args = args)
    }

    private val stateObserver = Observer<HeroDetailState> { state ->
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
            viewModel.sendAction(HeroDetailActions.OnResume)
        }))
    }

    private fun renderReady(state: HeroDetailState) {
        contentSection.update(heroDetailContentRenderer.renderItems(state, this.requireContext()) {

        })
    }

    private fun renderLoading() {
        contentSection.update(listOf(HeroLoadingItem))
    }

    override fun onResume() {
        super.onResume()
        viewModel.sendAction(HeroDetailActions.OnResume)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hero_detail, container, false)
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
