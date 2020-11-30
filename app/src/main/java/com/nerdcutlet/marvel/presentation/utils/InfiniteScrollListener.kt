package com.nerdcutlet.marvel.presentation.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class InfiniteScrollListener(
    private var linearLayoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    // The total number of items in the dataset after the last load
    private var previousTotal =
        0

    // True if we are still waiting for the last set of data to load.
    private var loading =
        true

    // The minimum amount of items to have below your current scroll position before loading more.
    private val visibleThreshold = VISIBLE_THRESHOLD

    private var firstVisibleItem = 0
    private var lastVisibleItem = 0
    private var totalItemCount = 0

    private val loadMore = Runnable { onLoadMore() }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dx > 0 || dy > 0) {

            // visibleItemCount = recyclerView.childCount
            totalItemCount = linearLayoutManager.itemCount

            firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

            if (loading) {
                if (totalItemCount > previousTotal || totalItemCount == 0) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }

            // End has been reached
            if (!loading && lastVisibleItem >= (totalItemCount - visibleThreshold)) {
                recyclerView.post(loadMore)
                loading = true
            }
        }
    }

    abstract fun onLoadMore()

    companion object {
        const val VISIBLE_THRESHOLD = 5
    }
}
