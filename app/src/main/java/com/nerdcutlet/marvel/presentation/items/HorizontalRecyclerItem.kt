package com.nerdcutlet.marvel.presentation.items

import androidx.recyclerview.widget.LinearLayoutManager
import com.nerdcutlet.marvel.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_horizontal_recycler.view.*

data class HorizontalRecyclerItem(
    val title: String,
    val adapter: GroupAdapter<GroupieViewHolder>
) : Item<GroupieViewHolder>() {

    override fun getLayout() = R.layout.item_horizontal_recycler

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.apply {
            item_horizontal_title.text = title

            horizontal_recycler.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this.adapter = this@HorizontalRecyclerItem.adapter
            }
        }
    }

    override fun getId(): Long {
        return adapter.itemCount.hashCode().toLong()
    }
}
