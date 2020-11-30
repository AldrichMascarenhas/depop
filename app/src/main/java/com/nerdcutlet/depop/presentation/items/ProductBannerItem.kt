package com.nerdcutlet.depop.presentation.items

import com.bumptech.glide.Glide
import com.nerdcutlet.depop.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_product_banner.view.*

data class ProductBannerItem(
    private val url: String
) : Item<GroupieViewHolder>() {

    override fun getLayout() = R.layout.item_product_banner

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            Glide.with(item_hero_banner.context)
                .load(url)
                .placeholder(R.drawable.placeholder_image)
                .into(item_hero_banner)
        }
    }
}
