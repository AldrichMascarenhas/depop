package com.nerdcutlet.depop.presentation.items

import android.view.ViewGroup
import com.nerdcutlet.depop.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_loading.view.*

data class LoadingItem(
    private val fullScreen: Boolean = false
) : Item<GroupieViewHolder>() {

    override fun getId() = hashCode().toLong()

    override fun getLayout(): Int = R.layout.item_loading

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {

            if (fullScreen) {
                val layoutParams = container.layoutParams
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            }
        }
    }
}
