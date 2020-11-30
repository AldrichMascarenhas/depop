package com.nerdcutlet.depop.presentation.items

import com.nerdcutlet.depop.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_product_loading.view.*

object ProductLoadingItem : Item<GroupieViewHolder>() {

    override fun getId() = hashCode().toLong()

    override fun getLayout(): Int = R.layout.item_product_loading

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {

            lottie_loading.setAnimation("lottie_animations/hero_loading.json")

            if (!lottie_loading.isAnimating) {
                lottie_loading.playAnimation()
            }
        }
    }
}
