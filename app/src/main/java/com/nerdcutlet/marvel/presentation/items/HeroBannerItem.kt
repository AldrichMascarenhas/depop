package com.nerdcutlet.marvel.presentation.items

import com.bumptech.glide.Glide
import com.nerdcutlet.marvel.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_hero_banner.view.*

data class HeroBannerItem(
    private val url: String
) : Item<GroupieViewHolder>() {

    override fun getLayout() = R.layout.item_hero_banner

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            Glide.with(item_hero_banner.context)
                .load(url)
                .placeholder(R.drawable.placeholder_image)
                .into(item_hero_banner)
        }
    }
}
